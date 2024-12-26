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
            ResultSet rs;
            try (Statement statement = databaseConfig.getCon().createStatement()) {
                String s = "insert into giocatore (nome_cognome,id_squadra) values ('" +
                        giocatoreModel.getNomeCognome() + "', " +
                        giocatoreModel.getSquadra().getIdSquadra() + ")";
                statement.executeUpdate(s);
                databaseConfig.getCon().commit();

                rs = statement.executeQuery("select * from giocatore where nome_cognome = '"
                        + giocatoreModel.getNomeCognome() + "'");

                if (rs.next()) {
                    giocatoreModel.setIdGiocatore(rs.getInt(1));
                    giocatoreModel.setNomeCognome(rs.getString("nome_cognome"));
                }
            }
        } catch (SQLException e) {
            if (databaseConfig.getCon() != null) {
                databaseConfig.getCon().rollback();
            }
            throw new RuntimeException(e);
        }
        return GiocatoreMapper.giocatoreModelToDTOExtended(giocatoreModel);
    }

    @Override
    public Set<GiocatoreDTOExtended> createAll(Set<GiocatoreDTOExtended> giocatoriDTOExtended) throws SQLException {
        Set<GiocatoreModel> giocatoriModel = giocatoriDTOExtended.stream()
                .map(GiocatoreMapper::giocatoreDtoExtendedToModel)
                .collect(Collectors.toSet());
        Set<GiocatoreModel> giocatoriResult = new HashSet<>();
        try {
            try (Statement statement = databaseConfig.getCon().createStatement()) {
                for (GiocatoreModel giocatoreModel : giocatoriModel) {
                    String s = "insert into giocatore (nome_cognome, id_squadra) values ('" +
                            giocatoreModel.getNomeCognome() + "', " +
                            giocatoreModel.getSquadra().getIdSquadra() + ")";
                    statement.executeUpdate(s);
                }
                databaseConfig.getCon().commit();

                GiocatoreModel giocatoreModel = giocatoriModel.stream().findAny().get();
                ResultSet rs = statement.executeQuery("select * from giocatore where id_squadra = "
                        + giocatoreModel.getSquadra().getIdSquadra());

                while (rs.next()) {
                    GiocatoreModel giocatoreModel1 = new GiocatoreModel();
                    giocatoreModel1.setIdGiocatore(rs.getInt("id"));
                    giocatoreModel1.setNomeCognome(rs.getString("nome_cognome"));
                    giocatoreModel1.setSquadra(giocatoreModel.getSquadra());

                    giocatoriResult.add(giocatoreModel1);
                }
            }
        } catch (SQLException e) {
            if (databaseConfig.getCon() != null) {
                databaseConfig.getCon().rollback();
            }
            throw new RuntimeException(e);
        }
        return giocatoriResult.stream()
                .map(GiocatoreMapper::giocatoreModelToDTOExtended)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<GiocatoreDTOExtended> readAllByTeam(SquadraDTOExtended squadraDTOExtended) throws SQLException {
        Set<GiocatoreDTOExtended> listaGiocatori = new HashSet<>();
        SquadraModel squadraModel = SquadraMapper.squadraDtoExtendedToModel(squadraDTOExtended);
        try {
            ResultSet resultGiocatori;
            try (Statement statement = databaseConfig.getCon().createStatement()) {
                String s = "select * from giocatore where id_squadra = "
                        + squadraModel.getIdSquadra();
                resultGiocatori = statement.executeQuery(s);


                while (resultGiocatori.next()) {
                    GiocatoreModel giocatoreModel = new GiocatoreModel();
                    giocatoreModel.setIdGiocatore(resultGiocatori.getInt("id"));
                    giocatoreModel.setNomeCognome(resultGiocatori.getString("nome_cognome"));
                    giocatoreModel.setNumeroAmmonizioni(resultGiocatori.getInt("numero_ammonizioni"));
                    giocatoreModel.setSquadra(squadraModel);
                    listaGiocatori.add(GiocatoreMapper.giocatoreModelToDTOExtended(giocatoreModel));
                }
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
            ResultSet rs;
            try (Statement statement = databaseConfig.getCon().createStatement()) {
                String s = "select * from squadra where nome = '" + nomeGiocatore + "'";
                rs = statement.executeQuery(s);

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
    public GiocatoreDTOExtended findGiocatoreById(Integer id) throws SQLException {
        GiocatoreModel giocatoreModel = new GiocatoreModel();
        try {
            ResultSet rs;
            try (Statement statement = databaseConfig.getCon().createStatement()) {
                String s = "select * from giocatore where id = " + id;
                rs = statement.executeQuery(s);

                if (rs.next()) {
                    giocatoreModel.setIdGiocatore(rs.getInt("id"));
                    giocatoreModel.setNomeCognome(rs.getString("nome_cognome"));
                    giocatoreModel.setNumeroAmmonizioni(rs.getInt("numero_ammonizioni"));
                }
            }
        } catch (SQLException e) {
            if (databaseConfig.getCon() != null) {
                databaseConfig.getCon().rollback();
            }
            throw new RuntimeException(e);
        }

        return GiocatoreMapper.giocatoreModelToDTOExtended(giocatoreModel);
    }

    @Override
    public void updateAmmonizioni(Integer id) throws SQLException {
        try {
            try (Statement statement = databaseConfig.getCon().createStatement()) {
                String s = "update giocatore set numero_ammonizioni = numero_ammonizioni +1" +
                        " where id = " + id;
                statement.executeUpdate(s);
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
