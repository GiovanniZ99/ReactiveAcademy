package it.reactive.academy.springMvc.repository.statement;

import it.reactive.academy.springMvc.configuration.DatabaseConfig;
import it.reactive.academy.springMvc.dto.extended.TorneoDTOExtended;
import it.reactive.academy.springMvc.mapper.TorneoMapper;
import it.reactive.academy.springMvc.model.TorneoModel;
import it.reactive.academy.springMvc.repository.dao.TorneoDao;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.List;

@Repository
public class TorneoDaoImpl implements TorneoDao {

    private final DatabaseConfig databaseConfig;

    public TorneoDaoImpl(DatabaseConfig databaseConfig) {
        this.databaseConfig = databaseConfig;
    }

    @Override
    public TorneoDTOExtended create(String nomeTorneo) throws SQLException {
        TorneoModel torneoModel = new TorneoModel();
        try {
            ResultSet rs;
            try (Statement statement = databaseConfig.getCon().createStatement()) {
                String s = "insert into torneo (nome_torneo) values('" + nomeTorneo + "')";
                statement.executeUpdate(s);
                databaseConfig.getCon().commit();
                rs = statement.executeQuery("select * from torneo where nome_torneo = '"
                        + nomeTorneo + "'");

                if (rs.next()) {
                    torneoModel.setIdTorneo(rs.getInt(1));
                    torneoModel.setNomeTorneo(rs.getString(2));
                }
            }
        } catch (SQLException e) {
            if (databaseConfig.getCon() != null) {
                databaseConfig.getCon().rollback();
            }
            throw new RuntimeException(e);
        }
        return TorneoMapper.torneoModelToDtoExtended(torneoModel);
    }

    @Override
    public TorneoDTOExtended findById(Integer idTorneo) throws SQLException {
        TorneoModel torneoModel = new TorneoModel();
        try {
            ResultSet rs;
            try (Statement statement = databaseConfig.getCon().createStatement()) {
                String s = "select * from torneo where id = " + idTorneo;
                rs = statement.executeQuery(s);

                if (rs.next()) {
                    torneoModel.setIdTorneo(rs.getInt(1));
                    torneoModel.setNomeTorneo(rs.getString(2));
                }
            }
        } catch (SQLException e) {
            if (databaseConfig.getCon() != null) {
                databaseConfig.getCon().rollback();
            }
            throw new RuntimeException(e);
        }
        return TorneoMapper.torneoModelToDtoExtended(torneoModel);
    }

    @Override
    public void delete(Integer id) throws SQLException {
        try {
            try (Statement statement = databaseConfig.getCon().createStatement()) {
                String s = "delete from squadra_torneo where id_torneo =" + id;
                statement.executeUpdate(s);
                String s1 = "delete from torneo where id = " + id;
                statement.executeUpdate(s1);
                databaseConfig.getCon().commit();
            }
        } catch (SQLException e) {
            if (databaseConfig.getCon() != null) {
                databaseConfig.getCon().rollback();
            }
            throw new RuntimeException(e);
        }
    }
}
