package it.reactive.academy.springMvc.repository.statement;

import it.reactive.academy.springMvc.configuration.DatabaseConfig;
import it.reactive.academy.springMvc.dto.extended.SquadraDTOExtended;
import it.reactive.academy.springMvc.mapper.SquadraMapper;
import it.reactive.academy.springMvc.model.SquadraModel;
import it.reactive.academy.springMvc.repository.dao.SquadraDao;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

@Repository
public class SquadraDaoImpl implements SquadraDao {

    private final DatabaseConfig databaseConfig;

    public SquadraDaoImpl(DatabaseConfig databaseConfig) {
        this.databaseConfig = databaseConfig;
    }

    @Override
    public SquadraDTOExtended create(SquadraDTOExtended squadraDTOExtended) throws SQLException {
        SquadraModel squadraModel = SquadraMapper.squadraDtoExtendedToModel(squadraDTOExtended);

        try {
            ResultSet rs;
            try (Statement statement = databaseConfig.getCon().createStatement()) {

                statement.executeUpdate("insert into squadra (nome, colori_sociali) values ('"
                        + squadraModel.getNome() + "', '" + squadraModel.getColoriSociali() + "')");
                databaseConfig.getCon().commit();

                String s = "Select id, nome, colori_sociali " +
                        "from squadra" +
                        " where nome = '"
                        + squadraModel.getNome()
                        + "' and  colori_sociali =  '" + squadraModel.getColoriSociali() + "'";
                rs = statement.executeQuery(s);

                if (rs.next()) {
                    squadraModel.setIdSquadra(rs.getInt(1));
                    squadraModel.setNome(rs.getString(2));
                    squadraModel.setColoriSociali(rs.getString(3));
                }
            }
        } catch (SQLException e) {
            databaseConfig.getCon().rollback();
            throw new RuntimeException(e);
        }
        SquadraDTOExtended squadraResult = SquadraMapper.squadraModelToDtoExtendended(squadraModel);
        squadraResult.setGiocatori(squadraDTOExtended.getGiocatori());
        return squadraResult;
    }

    @Override
    public List<SquadraDTOExtended> readAll() throws SQLException {
        List<SquadraDTOExtended> listaSquadra = new LinkedList<>();

        try {
            ResultSet rs;
            try (Statement statement = databaseConfig.getCon().createStatement()) {
                String s = "select * from squadra";
                rs = statement.executeQuery(s);

                while (rs.next()) {
                    SquadraModel squadraModel = new SquadraModel();
                    squadraModel.setIdSquadra(rs.getInt(1));
                    squadraModel.setNome(rs.getString(2));
                    squadraModel.setColoriSociali(rs.getString(3));
                    listaSquadra.add(SquadraMapper.squadraModelToDtoExtendended(squadraModel));
                }
            }
        } catch (SQLException e) {
            if (databaseConfig.getCon() != null) {
                databaseConfig.getCon().rollback();
            }
            throw new RuntimeException(e);
        }
        return listaSquadra;
    }

    @Override
    public SquadraDTOExtended findSquadraByOd(Integer idSquadra) throws SQLException {
        SquadraModel squadraModel = new SquadraModel();
        try {
            ResultSet rs;
            try (Statement statement = databaseConfig.getCon().createStatement()) {
                String s = "select * from squadra where id = " + idSquadra;
                rs = statement.executeQuery(s);

                if (rs.next()) {
                    squadraModel.setIdSquadra(rs.getInt("id"));
                    squadraModel.setNome(rs.getString("nome"));
                    squadraModel.setColoriSociali(rs.getString("colori_sociali"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (databaseConfig.getCon() != null) {
            databaseConfig.getCon().rollback();
        }
        return SquadraMapper.squadraModelToDtoExtendended(squadraModel);
    }

    @Override
    public boolean checkSquadraByName(String nomeSquadra) throws SQLException {
        try {
            try (Statement statement = databaseConfig.getCon().createStatement()) {
                String s = "select * from squadra where nome = '" + nomeSquadra + "'";
                ResultSet rs = statement.executeQuery(s);

                return rs.next();
            }
        } catch (SQLException e) {
            if (databaseConfig.getCon() != null) {
                databaseConfig.getCon().rollback();
            }
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
        try {
            try (Statement statement = databaseConfig.getCon().createStatement()) {
                String s = "delete from squadra_torneo where id_squadra =" + id;
                statement.executeUpdate(s);
                String s1 = "delete from tifoseria where id_squadra = " + id;
                statement.executeUpdate(s1);
                String s2 = "delete from giocatore where id_squadra = " + id;
                statement.executeUpdate(s2);
                String s3 = "delete from squadra where id = " + id;
                statement.executeUpdate(s3);
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