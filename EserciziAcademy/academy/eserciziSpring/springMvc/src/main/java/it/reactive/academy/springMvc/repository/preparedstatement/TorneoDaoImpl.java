package it.reactive.academy.springMvc.repository.preparedstatement;

import it.reactive.academy.springMvc.configuration.Costanti;
import it.reactive.academy.springMvc.configuration.DatabaseConfig;
import it.reactive.academy.springMvc.dto.extended.TorneoDTOExtended;
import it.reactive.academy.springMvc.mapper.TorneoMapper;
import it.reactive.academy.springMvc.model.TorneoModel;
import it.reactive.academy.springMvc.repository.dao.TorneoDao;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
@Profile(Costanti.TORNEO_DAO_JDBC_PREPAREDSTATEMENT)
public class TorneoDaoImpl implements TorneoDao {

    private final DatabaseConfig databaseConfig;

    public TorneoDaoImpl(DatabaseConfig databaseConfig) {
        this.databaseConfig = databaseConfig;
    }

    @Override
    public TorneoDTOExtended create(String nomeTorneo) throws SQLException {
        TorneoModel torneoModel = new TorneoModel();
        try (PreparedStatement ps = databaseConfig.getCon().prepareStatement(
                "insert into torneo (nome_torneo) values (?)",
                Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, nomeTorneo);
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    torneoModel.setIdTorneo(rs.getInt(1));
                    torneoModel.setNomeTorneo(rs.getString(2));
                }
            }
        }
        return TorneoMapper.torneoModelToDtoExtended(torneoModel);
    }

    @Override
    public TorneoDTOExtended findById(Integer idTorneo) throws SQLException {
        TorneoModel torneoModel = new TorneoModel();
        try (PreparedStatement ps = databaseConfig.getCon().prepareStatement(
                "select id, nome_torneo from torneo where id = ?")) {

            ps.setInt(1, idTorneo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    torneoModel.setIdTorneo(rs.getInt(1));
                    torneoModel.setNomeTorneo(rs.getString(2));
                }
            }
        }
        return TorneoMapper.torneoModelToDtoExtended(torneoModel);
    }

    @Override
    public void delete(Integer id) throws SQLException {
        try (PreparedStatement ps1 = databaseConfig.getCon().prepareStatement(
                "delete from squadra_torneo where id_torneo = ?")) {

            ps1.setInt(1, id);
            ps1.executeUpdate();

            try (PreparedStatement ps2 = databaseConfig.getCon().prepareStatement(
                    "delete from torneo where id = ?")) {

                ps2.setInt(1, id);
                ps2.executeUpdate();
            }
        }
    }
}
