package it.reactive.academy.springMvc.repository.statement;

import it.reactive.academy.springMvc.configuration.DatabaseConfig;
import it.reactive.academy.springMvc.dto.extended.SquadraDTOExtended;
import it.reactive.academy.springMvc.dto.extended.TifoseriaDTOExtended;
import it.reactive.academy.springMvc.mapper.SquadraMapper;
import it.reactive.academy.springMvc.mapper.TifoseriaMapper;
import it.reactive.academy.springMvc.model.SquadraModel;
import it.reactive.academy.springMvc.model.TifoseriaModel;
import it.reactive.academy.springMvc.repository.dao.TifoseriaDao;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Repository
public class TifoseriaDaoImpl implements TifoseriaDao {

    private final DatabaseConfig databaseConfig;

    public TifoseriaDaoImpl(DatabaseConfig databaseConfig) {
        this.databaseConfig = databaseConfig;
    }

    @Override
    public TifoseriaDTOExtended createWithTeam(TifoseriaDTOExtended tifoseriaDTOExtended, Integer idSquadra) throws SQLException {
        TifoseriaModel tifoseriaModel = TifoseriaMapper.tifoseriaDtoExtendedToModel(tifoseriaDTOExtended);
        try {
            ResultSet rs;
            try (Statement statement = databaseConfig.getCon().createStatement()) {
                String s = "insert into tifoseria (nome_tifoseria, id_squadra) values('" +
                        tifoseriaModel.getNomeTifoseria() + "', " +
                        idSquadra + ")";
                statement.executeUpdate(s);
                databaseConfig.getCon().commit();
                rs = statement.executeQuery("Select * from tifoseria where id_squadra = "
                        + idSquadra);

                if (rs.next()) {
                    tifoseriaModel.setIdTifoseria(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            if (databaseConfig.getCon() != null) {
                databaseConfig.getCon().rollback();
            }
            throw new RuntimeException(e);
        }
        return TifoseriaMapper.tifoseriaModelToDtoExtended(tifoseriaModel);
    }

    public TifoseriaDTOExtended readByTeam(SquadraDTOExtended squadraDTOExtended) throws SQLException {
        TifoseriaModel tifoseriaModel = new TifoseriaModel();
        SquadraModel squadraModel = SquadraMapper.squadraDtoExtendedToModel(squadraDTOExtended);
        try {
            try (Statement statement = databaseConfig.getCon().createStatement()) {
                String s = "select * from tifoseria where id_squadra = " + squadraModel.getIdSquadra();
                ResultSet rs = statement.executeQuery(s);
                if (rs.next()) {
                    tifoseriaModel.setIdTifoseria(rs.getInt("id"));
                    tifoseriaModel.setNomeTifoseria(rs.getString("nome_tifoseria"));
                }
            }
        } catch (SQLException e) {
            if (databaseConfig.getCon() != null) {
                databaseConfig.getCon().rollback();
            }
            throw new RuntimeException(e);
        }
        return TifoseriaMapper.tifoseriaModelToDtoExtended(tifoseriaModel);
    }

    @Override
    public TifoseriaDTOExtended updateName(String name, Integer idSquadra) throws SQLException {
        TifoseriaModel tifoseriaModel = new TifoseriaModel();
        try {
            try (Statement statement = databaseConfig.getCon().createStatement()) {
                String s = "update tifoseria set nome_tifoseria '" +
                        name + "' where id_squadra = " + idSquadra;
                statement.executeUpdate(s);
                databaseConfig.getCon().commit();

                ResultSet rs = statement.executeQuery("select * from tifoseria where nome_tifoseria = '" +
                        name + "'");
                if (rs.next()) {
                    tifoseriaModel.setIdTifoseria(rs.getInt("id"));
                    tifoseriaModel.setNomeTifoseria(rs.getString("nome_tifoseria"));
                }
            }
        } catch (SQLException e) {
            if (databaseConfig.getCon() != null) {
                databaseConfig.getCon().rollback();
            }
            throw new RuntimeException(e);
        }
        return TifoseriaMapper.tifoseriaModelToDtoExtended(tifoseriaModel);
    }
}