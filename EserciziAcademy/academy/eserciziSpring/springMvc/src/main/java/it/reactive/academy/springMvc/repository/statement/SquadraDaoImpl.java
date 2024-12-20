package it.reactive.academy.springMvc.repository.statement;

import it.reactive.academy.springMvc.configuration.DatabaseConfig;
import it.reactive.academy.springMvc.dto.extended.GiocatoreDTOExtended;
import it.reactive.academy.springMvc.dto.extended.SquadraDTOExtended;
import it.reactive.academy.springMvc.mapper.SquadraMapper;
import it.reactive.academy.springMvc.model.SquadraModel;
import it.reactive.academy.springMvc.repository.dao.SquadraDao;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final GiocatoreDaoImpl giocatoreDao;

    public SquadraDaoImpl(DatabaseConfig databaseConfig, GiocatoreDaoImpl giocatoreDao) {
        this.databaseConfig = databaseConfig;
        this.giocatoreDao = giocatoreDao;
    }

    @Override
    public SquadraDTOExtended create(SquadraDTOExtended squadraDTOExtended) {
        SquadraModel squadraModel = SquadraMapper.squadraDtoExtendedToModel(squadraDTOExtended);
        Statement statement;
        try {
            statement = databaseConfig.getCon().createStatement();

            statement.executeUpdate("insert into squadra (nome, colori_sociali) values ('"
                    + squadraModel.getNome() + "', '" + squadraModel.getColoriSociali() + "')");
            databaseConfig.getCon().commit();

            ResultSet rs = statement.executeQuery("Select id, nome, colori_sociali " +
                    "from squadra" +
                    " where nome = '"
                    + squadraModel.getNome()
                    + "' and  colori_sociali =  '" + squadraModel.getColoriSociali() + "'");
            if (rs.next()) {
                squadraModel.setIdSquadra(rs.getInt(1));
                squadraModel.setNome(rs.getString(2));
                squadraModel.setColoriSociali(rs.getString(3));
            }
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return SquadraMapper.squadraModelToDtoExtendended(squadraModel);
    }

    @Override
    public List<SquadraDTOExtended> readTeamsAndPlayers() {
        List<SquadraDTOExtended> listaSquadra = new LinkedList<>();

        try {
            Statement statement = databaseConfig.getCon().createStatement();

            ResultSet rs = statement.executeQuery("select * from squadra");

            while (rs.next()) {
                SquadraModel squadraModel = new SquadraModel();
                squadraModel.setIdSquadra(rs.getInt(1));
                squadraModel.setNome(rs.getString(2));
                squadraModel.setColoriSociali(rs.getString(3));

                Set<GiocatoreDTOExtended> listaGiocatori = giocatoreDao.readAll(squadraModel);
                SquadraDTOExtended squadraDTOExtended = SquadraMapper.squadraModelToDtoExtendended(squadraModel);
                squadraDTOExtended.setGiocatori(listaGiocatori);
                listaSquadra.add(squadraDTOExtended);
            }
            statement.close();
            return listaSquadra;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<SquadraDTOExtended> readAll() {
        List<SquadraDTOExtended> listaSquadra = new LinkedList<>();

        try {
            Statement statement = databaseConfig.getCon().createStatement();
            ResultSet rs = statement.executeQuery("select * from squadra");

            while (rs.next()) {
                SquadraModel squadraModel = new SquadraModel();
                squadraModel.setIdSquadra(rs.getInt(1));
                squadraModel.setNome(rs.getString(2));
                squadraModel.setColoriSociali(rs.getString(3));
                listaSquadra.add(SquadraMapper.squadraModelToDtoExtendended(squadraModel));
            }
            statement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaSquadra;
    }

    @Override
    public SquadraDTOExtended update(int id, SquadraDTOExtended squadraDTOExtended) {
        return null;
    }

    @Override
    public SquadraDTOExtended delete(int id) {
        return null;
    }

    public boolean findSquadraByName(String nomeSquadra) {
        try {
            Statement statement = databaseConfig.getCon().createStatement();
            ResultSet rs = statement.executeQuery("select * from squadra where nome = " + nomeSquadra);
            statement.close();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
