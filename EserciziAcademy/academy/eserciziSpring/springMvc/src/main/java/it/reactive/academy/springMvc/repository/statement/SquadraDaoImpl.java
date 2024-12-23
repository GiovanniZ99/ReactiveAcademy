package it.reactive.academy.springMvc.repository.statement;

import it.reactive.academy.springMvc.configuration.DatabaseConfig;
import it.reactive.academy.springMvc.dto.extended.GiocatoreDTOExtended;
import it.reactive.academy.springMvc.dto.extended.SquadraDTOExtended;
import it.reactive.academy.springMvc.mapper.SquadraMapper;
import it.reactive.academy.springMvc.model.SquadraModel;
import it.reactive.academy.springMvc.repository.dao.GiocatoreDao;
import it.reactive.academy.springMvc.repository.dao.SquadraDao;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Repository
public class SquadraDaoImpl implements SquadraDao {

    private final DatabaseConfig databaseConfig;

    private final GiocatoreDao giocatoreDao;

    public SquadraDaoImpl(DatabaseConfig databaseConfig, GiocatoreDao giocatoreDao) {
        this.databaseConfig = databaseConfig;
        this.giocatoreDao = giocatoreDao;
    }

    @Override
    public SquadraDTOExtended create(SquadraDTOExtended squadraDTOExtended) throws SQLException {
        SquadraModel squadraModel = SquadraMapper.squadraDtoExtendedToModel(squadraDTOExtended);

        try {
            Statement statement = databaseConfig.getCon().createStatement();

            statement.executeUpdate("insert into squadra (nome, colori_sociali) values ('"
                    + squadraModel.getNome() + "', '" + squadraModel.getColoriSociali() + "')");
            databaseConfig.getCon().commit();

            String s = "Select id, nome, colori_sociali " +
                    "from squadra" +
                    " where nome = '"
                    + squadraModel.getNome()
                    + "' and  colori_sociali =  '" + squadraModel.getColoriSociali() + "'";
            ResultSet rs = statement.executeQuery(s);
            if (rs.next()) {
                squadraModel.setIdSquadra(rs.getInt(1));
                squadraModel.setNome(rs.getString(2));
                squadraModel.setColoriSociali(rs.getString(3));
            }
        } catch (SQLException e) {
            databaseConfig.getCon().rollback();
            throw new RuntimeException(e);
        }
        return SquadraMapper.squadraModelToDtoExtendended(squadraModel);
    }

    @Override
    public List<SquadraDTOExtended> readTeamsAndPlayers() throws SQLException {
        List<SquadraDTOExtended> listaSquadra = new LinkedList<>();

        try {
            Statement statement = databaseConfig.getCon().createStatement();
            String s = "select * from squadra";
            ResultSet rs = statement.executeQuery(s);

            while (rs.next()) {
                SquadraModel squadraModel = new SquadraModel();
                squadraModel.setIdSquadra(rs.getInt(1));
                squadraModel.setNome(rs.getString(2));
                squadraModel.setColoriSociali(rs.getString(3));

                SquadraDTOExtended squadraDTOExtended = SquadraMapper.squadraModelToDtoExtendended(squadraModel);
                Set<GiocatoreDTOExtended> listaGiocatori = giocatoreDao.readAllByTeam(squadraDTOExtended);
                squadraDTOExtended.setGiocatori(listaGiocatori);
                listaSquadra.add(squadraDTOExtended);
            }
            databaseConfig.closeCon();
            return listaSquadra;

        } catch (SQLException e) {
            if (databaseConfig.getCon() != null) {
                databaseConfig.getCon().rollback();
            }
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<SquadraDTOExtended> readAll() throws SQLException {
        List<SquadraDTOExtended> listaSquadra = new LinkedList<>();

        try {
            Statement statement = databaseConfig.getCon().createStatement();
            String s = "select * from squadra";
            ResultSet rs = statement.executeQuery(s);

            while (rs.next()) {
                SquadraModel squadraModel = new SquadraModel();
                squadraModel.setIdSquadra(rs.getInt(1));
                squadraModel.setNome(rs.getString(2));
                squadraModel.setColoriSociali(rs.getString(3));
                listaSquadra.add(SquadraMapper.squadraModelToDtoExtendended(squadraModel));
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
            Statement statement = databaseConfig.getCon().createStatement();
            String s = "select * from squadra where id = " + idSquadra;
            ResultSet rs = statement.executeQuery(s);
            if(rs.next()){
                squadraModel.setIdSquadra(rs.getInt("id"));
                squadraModel.setNome(rs.getString("nome"));
                squadraModel.setColoriSociali(rs.getString("colori_sociali"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }if (databaseConfig.getCon() != null) {
            databaseConfig.getCon().rollback();
        }
        return SquadraMapper.squadraModelToDtoExtendended(squadraModel);
    }

    @Override
    public boolean checkSquadraByName(String nomeSquadra) throws SQLException {
        try {
            Statement statement = databaseConfig.getCon().createStatement();
            String s = "select * from squadra where nome = '" + nomeSquadra + "'";
            ResultSet rs = statement.executeQuery(s);
            return rs.next();

        } catch (SQLException e) {
            if (databaseConfig.getCon() != null) {
                databaseConfig.getCon().rollback();
            }
            throw new RuntimeException(e);
        }
    }

    @Override
    public SquadraDTOExtended update(int id, SquadraDTOExtended squadraDTOExtended) {
        return null;
    }

    @Override
    public SquadraDTOExtended delete(int id) {
        return null;
    }
}
