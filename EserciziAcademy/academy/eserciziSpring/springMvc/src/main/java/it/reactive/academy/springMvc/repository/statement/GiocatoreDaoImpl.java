package it.reactive.academy.springMvc.repository.statement;

import it.reactive.academy.springMvc.configuration.DatabaseConfig;
import it.reactive.academy.springMvc.dto.extended.GiocatoreDTOExtended;
import it.reactive.academy.springMvc.dto.extended.SquadraDTOExtended;
import it.reactive.academy.springMvc.mapper.GiocatoreMapper;
import it.reactive.academy.springMvc.mapper.SquadraMapper;
import it.reactive.academy.springMvc.model.GiocatoreModel;
import it.reactive.academy.springMvc.model.SquadraModel;
import it.reactive.academy.springMvc.repository.dao.GiocatoreDao;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class GiocatoreDaoImpl implements GiocatoreDao {

    private final DatabaseConfig databaseConfig;

    public GiocatoreDaoImpl(DatabaseConfig databaseConfig) {
        this.databaseConfig = databaseConfig;
    }

    @Override
    public GiocatoreDTOExtended create(GiocatoreDTOExtended giocatoreDTOExtended) throws SQLException {
        GiocatoreModel giocatoreModel = GiocatoreMapper.giocatoreDtoExtendedToModel(giocatoreDTOExtended);
        try {
            Statement statement = databaseConfig.getCon().createStatement();
            String s = "insert into giocatore (nome_cognome, numero_ammonizioni, id_squadra) values ('" +
                    giocatoreModel.getNomeCognome() + "', " +
                    giocatoreModel.getNumeroAmmonizioni() + ", " +
                    giocatoreModel.getSquadra().getIdSquadra() + ")";
            statement.executeUpdate(s);
            databaseConfig.getCon().commit();

            ResultSet rs = statement.executeQuery("select * from giocatore where nome_cognome = '"
                    + giocatoreModel.getNomeCognome() + "'");
            if (rs.next()) {
                giocatoreModel.setIdGiocatore(rs.getInt(1));
                giocatoreModel.setNomeCognome(rs.getString("nome_cognome"));
                giocatoreModel.setNumeroAmmonizioni(rs.getInt("numero_ammonizioni"));
            }

        } catch (SQLException e) {
            if (databaseConfig.getCon() != null) {
                databaseConfig.getCon().rollback();
            }
            throw new RuntimeException(e);
        } finally {
            databaseConfig.getCon().close();
        }
        return GiocatoreMapper.giocatoreModelToDTOExtended(giocatoreModel);
    }

    @Override
    public Set<GiocatoreDTOExtended> createAll(Set<GiocatoreDTOExtended> giocatoriDTOExtended) throws SQLException {
        Set<GiocatoreModel> giocatoriModel = giocatoriDTOExtended.stream()
                .map(GiocatoreMapper::giocatoreDtoExtendedToModel)
                .collect(Collectors.toSet());

        try {
            Statement statement = databaseConfig.getCon().createStatement();
            for (GiocatoreModel giocatoreModel : giocatoriModel) {
                String s = "insert into giocatore (nome_cognome, numero_ammonizioni, id_squadra) values ('" +
                        giocatoreModel.getNomeCognome() + "', " +
                        giocatoreModel.getNumeroAmmonizioni() + ", " +
                        giocatoreModel.getSquadra().getIdSquadra() + ")";
                statement.executeUpdate(s);
                databaseConfig.getCon().commit();

                ResultSet rs = statement.executeQuery("select * from giocatore where id_squadra = "
                        + giocatoreModel.getSquadra().getIdSquadra());

                while (rs.next()) {
                    for (GiocatoreModel giocatoreResult : giocatoriModel) {
                        giocatoreResult.setIdGiocatore(rs.getInt(1));
                        giocatoreResult.setNomeCognome(rs.getString("nome_cognome"));
                        giocatoreResult.setNumeroAmmonizioni(rs.getInt("numero_ammonizioni"));
                        giocatoreResult.setSquadra(giocatoreModel.getSquadra());
                    }
                }
            }

        } catch (SQLException e) {
            if (databaseConfig.getCon() != null) {
                databaseConfig.getCon().rollback();
            }
            throw new RuntimeException(e);
        } finally {
            databaseConfig.closeCon();
        }
        return giocatoriModel.stream()
                .map(GiocatoreMapper::giocatoreModelToDTOExtended)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<GiocatoreDTOExtended> readAll() throws SQLException {
        Set<GiocatoreDTOExtended> listaGiocatori = new HashSet<>();
        try {
            Statement statement = databaseConfig.getCon().createStatement();
            String s = "select * from giocatore";
            ResultSet resultGiocatori = statement.executeQuery(s);

            while (resultGiocatori.next()) {
                GiocatoreModel giocatoreModel = new GiocatoreModel();
                giocatoreModel.setIdGiocatore(resultGiocatori.getInt("id"));
                giocatoreModel.setNomeCognome(resultGiocatori.getString("nome_cognome"));
                giocatoreModel.setNumeroAmmonizioni(resultGiocatori.getInt("numero_ammonizioni"));
                listaGiocatori.add(GiocatoreMapper.giocatoreModelToDTOExtended(giocatoreModel));
            }
        } catch (SQLException e) {
            if (databaseConfig.getCon() != null) {
                databaseConfig.getCon().rollback();
            }
            throw new RuntimeException(e);
        }
        return listaGiocatori;
    }

    @Override
    public Set<GiocatoreDTOExtended> readAllByTeam(SquadraDTOExtended squadraDTOExtended) throws SQLException {
        Set<GiocatoreDTOExtended> listaGiocatori = new HashSet<>();
        SquadraModel squadraModel = SquadraMapper.squadraDtoExtendedToModel(squadraDTOExtended);
        try {
            Statement statement = databaseConfig.getCon().createStatement();
            String s = "select * from giocatore where id_squadra = "
                    + squadraModel.getIdSquadra();
            ResultSet resultGiocatori = statement.executeQuery(s);

            while (resultGiocatori.next()) {
                GiocatoreModel giocatoreModel = new GiocatoreModel();
                giocatoreModel.setIdGiocatore(resultGiocatori.getInt("id"));
                giocatoreModel.setNomeCognome(resultGiocatori.getString("nome_cognome"));
                giocatoreModel.setNumeroAmmonizioni(resultGiocatori.getInt("numero_ammonizioni"));
                giocatoreModel.setSquadra(squadraModel);
                listaGiocatori.add(GiocatoreMapper.giocatoreModelToDTOExtended(giocatoreModel));
            }
        } catch (SQLException e) {
            if (databaseConfig.getCon() != null) {
                databaseConfig.getCon().rollback();
            }
            throw new RuntimeException(e);
        }
        return listaGiocatori;
    }

    public boolean checkdByName(String nomeGiocatore) throws SQLException {
        try {
            Statement statement = databaseConfig.getCon().createStatement();
            String s = "select * from squadra where nome = '" + nomeGiocatore + "'";
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
    public GiocatoreDTOExtended findGiocatoreById(Integer id) throws SQLException {
        GiocatoreModel giocatoreModel = new GiocatoreModel();
        try {
            Statement statement = databaseConfig.getCon().createStatement();
            String s = "select * from squadra where id = " + id;
            ResultSet rs = statement.executeQuery(s);
            if (rs.next()) {
                giocatoreModel.setIdGiocatore(rs.getInt("id"));
                giocatoreModel.setNomeCognome(rs.getString("nome_cognome"));
                giocatoreModel.setNumeroAmmonizioni(rs.getInt("numero_ammonizioni"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (databaseConfig.getCon() != null) {
            databaseConfig.getCon().rollback();
        }
        return GiocatoreMapper.giocatoreModelToDTOExtended(giocatoreModel);
    }

    @Override
    public GiocatoreDTOExtended updateAmmonizioni(int id) {
        GiocatoreDTOExtended giocatoreDTOExtended;
        try {
            giocatoreDTOExtended = findGiocatoreById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        GiocatoreModel giocatoreModel = GiocatoreMapper.giocatoreDtoExtendedToModel(giocatoreDTOExtended);
        try {
            Statement statement = databaseConfig.getCon().createStatement();
            String s = "update giocatore set numero_ammonizioni = numero_ammonizioni +1" +
                    " where id = " + id;
            statement.executeUpdate(s);

           ResultSet rs = statement.executeQuery("select * from giocatore where id = " + id);
           if(rs.next()) {
               giocatoreModel.setIdGiocatore(rs.getInt("id"));
               giocatoreModel.setNomeCognome(rs.getString("nome_cognome"));
               giocatoreModel.setNumeroAmmonizioni(rs.getInt("numero_ammonizioni"));
           }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return GiocatoreMapper.giocatoreModelToDTOExtended(giocatoreModel);
    }

    @Override
    public GiocatoreDTOExtended delete(int id) {
        return null;
    }
}
