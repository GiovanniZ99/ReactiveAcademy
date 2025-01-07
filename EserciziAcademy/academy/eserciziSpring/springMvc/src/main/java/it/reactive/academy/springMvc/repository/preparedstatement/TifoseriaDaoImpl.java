package it.reactive.academy.springMvc.repository.preparedstatement;

import it.reactive.academy.springMvc.configuration.Costanti;
import it.reactive.academy.springMvc.configuration.DatabaseConfig;
import it.reactive.academy.springMvc.dto.extended.SquadraDTOExtended;
import it.reactive.academy.springMvc.dto.extended.TifoseriaDTOExtended;
import it.reactive.academy.springMvc.mapper.SquadraMapper;
import it.reactive.academy.springMvc.mapper.TifoseriaMapper;
import it.reactive.academy.springMvc.model.SquadraModel;
import it.reactive.academy.springMvc.model.TifoseriaModel;
import it.reactive.academy.springMvc.repository.dao.TifoseriaDao;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
@Profile(Costanti.TORNEO_DAO_JDBC_PREPAREDSTATEMENT)
public class TifoseriaDaoImpl implements TifoseriaDao {

    private final DatabaseConfig databaseConfig;

    public TifoseriaDaoImpl(DatabaseConfig databaseConfig) {
        this.databaseConfig = databaseConfig;
    }

    @Override
    public TifoseriaDTOExtended createWithTeam(TifoseriaDTOExtended tifoseriaDTOExtended, Integer idSquadra) throws SQLException {
        TifoseriaModel tifoseriaModel = TifoseriaMapper.tifoseriaDtoExtendedToModel(tifoseriaDTOExtended);
        try (PreparedStatement ps = databaseConfig.getCon().prepareStatement(
                "insert into tifoseria (nome_tifoseria, id_squadra) values (?, ?)",
                Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, tifoseriaModel.getNomeTifoseria());
            ps.setInt(2, idSquadra);
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    tifoseriaModel.setIdTifoseria(rs.getInt(1));
                }
            }
        }
        return TifoseriaMapper.tifoseriaModelToDtoExtended(tifoseriaModel);
    }

    @Override
    public TifoseriaDTOExtended readByTeam(SquadraDTOExtended squadraDTOExtended) throws SQLException {
        TifoseriaModel tifoseriaModel = new TifoseriaModel();
        SquadraModel squadraModel = SquadraMapper.squadraDtoExtendedToModel(squadraDTOExtended);
        try (PreparedStatement ps = databaseConfig.getCon().prepareStatement(
                "select id, nome_tifoseria from tifoseria where id_squadra = ?")) {
            ps.setInt(1, squadraModel.getIdSquadra());

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    tifoseriaModel.setIdTifoseria(rs.getInt("id"));
                    tifoseriaModel.setNomeTifoseria(rs.getString("nome_tifoseria"));
                }
            }
        }
        return TifoseriaMapper.tifoseriaModelToDtoExtended(tifoseriaModel);
    }

    @Override
    public TifoseriaDTOExtended updateName(String name, Integer idSquadra) throws SQLException {
        TifoseriaModel tifoseriaModel = new TifoseriaModel();
        try (PreparedStatement ps = databaseConfig.getCon().prepareStatement(
                "update tifoseria set nome_tifoseria = ? where id_squadra = ?",
                Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, name);
            ps.setInt(2, idSquadra);
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    tifoseriaModel.setIdTifoseria(rs.getInt(1));
                    tifoseriaModel.setNomeTifoseria(name);
                }
            }

        }

        return TifoseriaMapper.tifoseriaModelToDtoExtended(tifoseriaModel);
    }

}
