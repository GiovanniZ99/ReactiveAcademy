package it.reactive.academy.springMvc.repository.preparedstatement;

import it.reactive.academy.springMvc.utility.Costanti;
import it.reactive.academy.springMvc.dto.extended.TorneoDTOExtended;
import it.reactive.academy.springMvc.utility.mapper.TorneoMapper;
import it.reactive.academy.springMvc.model.TorneoModel;
import it.reactive.academy.springMvc.repository.dao.TorneoDao;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;

import java.sql.*;
import java.util.Objects;

@Repository
@Profile(Costanti.TORNEO_DAO_JDBC_PREPAREDSTATEMENT)
public class TorneoDaoImpl implements TorneoDao {

    private final PlatformTransactionManager transactionManager;

    public TorneoDaoImpl(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }


    @Override
    public TorneoDTOExtended create(String nomeTorneo) throws SQLException {
        TorneoModel torneoModel = new TorneoModel();
        torneoModel.setNomeTorneo(nomeTorneo);

        Connection con = DataSourceUtils.getConnection(Objects.requireNonNull(((DataSourceTransactionManager) transactionManager).getDataSource()));
        try (PreparedStatement ps = con.prepareStatement(
                "insert into torneo (nome_torneo) values (?)",
                Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, nomeTorneo);
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    torneoModel.setIdTorneo(rs.getInt(1));
                }
            }
        }
        return TorneoMapper.torneoModelToDtoExtended(torneoModel);
    }

    @Override
    public TorneoDTOExtended findById(Integer idTorneo) throws SQLException {
        TorneoModel torneoModel = new TorneoModel();

        Connection con = DataSourceUtils.getConnection(Objects.requireNonNull(((DataSourceTransactionManager) transactionManager).getDataSource()));
        try (PreparedStatement ps = con.prepareStatement(
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

        Connection con = DataSourceUtils.getConnection(Objects.requireNonNull(((DataSourceTransactionManager) transactionManager).getDataSource()));
        try (PreparedStatement ps1 = con.prepareStatement(
                "delete from squadra_torneo where id_torneo = ?")) {

            ps1.setInt(1, id);
            ps1.executeUpdate();

            try (PreparedStatement ps2 = con.prepareStatement(
                    "delete from torneo where id = ?")) {

                ps2.setInt(1, id);
                ps2.executeUpdate();
            }
        }
    }
}
