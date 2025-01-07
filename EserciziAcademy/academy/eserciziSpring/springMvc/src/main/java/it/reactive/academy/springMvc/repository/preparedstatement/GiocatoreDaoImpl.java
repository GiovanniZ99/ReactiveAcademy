package it.reactive.academy.springMvc.repository.preparedstatement;

import it.reactive.academy.springMvc.configuration.DatabaseConfig;
import it.reactive.academy.springMvc.dto.extended.GiocatoreDTOExtended;
import it.reactive.academy.springMvc.dto.extended.SquadraDTOExtended;
import it.reactive.academy.springMvc.mapper.GiocatoreMapper;
import it.reactive.academy.springMvc.mapper.SquadraMapper;
import it.reactive.academy.springMvc.model.GiocatoreModel;
import it.reactive.academy.springMvc.model.SquadraModel;
import it.reactive.academy.springMvc.repository.dao.GiocatoreDao;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
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
            try (PreparedStatement ps = databaseConfig.getCon().prepareStatement(
                    "insert into giocatore (nome_cognome, id_squadra) values (?, ?)",
                    Statement.RETURN_GENERATED_KEYS)) {

                ps.setString(1, giocatoreModel.getNomeCognome());
                ps.setInt(2, giocatoreModel.getSquadra().getIdSquadra());
                ps.executeUpdate();

                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        giocatoreModel.setIdGiocatore(rs.getInt(1));
                    }
                }
                databaseConfig.getCon().commit();
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

        try (PreparedStatement psInsert = databaseConfig.getCon().prepareStatement(
                "insert into giocatore (nome_cognome, id_squadra) values (?, ?)", Statement.RETURN_GENERATED_KEYS)) {

            for (GiocatoreModel giocatoreModel : giocatoriModel) {
                psInsert.setString(1, giocatoreModel.getNomeCognome());
                psInsert.setInt(2, giocatoreModel.getSquadra().getIdSquadra());
            }

            psInsert.executeUpdate();
            databaseConfig.getCon().commit();

            try (ResultSet rs = psInsert.getGeneratedKeys()) {
                while (rs.next()) {
                    GiocatoreModel giocatoreModel = new GiocatoreModel();
                    giocatoreModel.setIdGiocatore(rs.getInt(1));
                    giocatoreModel.setNomeCognome(rs.getString(2));
                    giocatoreModel.setSquadra(giocatoriModel.iterator().next().getSquadra());
                    giocatoriResult.add(giocatoreModel);
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

        try (PreparedStatement ps = databaseConfig.getCon().prepareStatement(
                "select * from giocatore where id_squadra = ?")) {
            ps.setInt(1, squadraModel.getIdSquadra());
            ResultSet resultGiocatori = ps.executeQuery();

            while (resultGiocatori.next()) {
                GiocatoreModel giocatoreModel = new GiocatoreModel();
                giocatoreModel.setIdGiocatore(resultGiocatori.getInt("id"));
                giocatoreModel.setNomeCognome(resultGiocatori.getString("nome_cognome"));
                giocatoreModel.setNumeroAmmonizioni(resultGiocatori.getInt("numero_ammonizioni"));
                giocatoreModel.setSquadra(squadraModel);
                listaGiocatori.add(GiocatoreMapper.giocatoreModelToDTOExtended(giocatoreModel));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaGiocatori;
    }

    public boolean checkByName(String nomeGiocatore) throws SQLException {
        try (PreparedStatement ps = databaseConfig.getCon().prepareStatement(
                "select id from squadra where nome = ?")) {
            ps.setString(1, nomeGiocatore);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public GiocatoreDTOExtended findGiocatoreById(Integer id) throws SQLException {
        GiocatoreModel giocatoreModel = new GiocatoreModel();

        try (PreparedStatement ps = databaseConfig.getCon().prepareStatement(
                "select id, nome_cognome, numero_ammonizioni from giocatore where id = ?")) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
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
    public void updateAmmonizioni(Integer id) throws SQLException {
        try (PreparedStatement ps = databaseConfig.getCon().prepareStatement(
                "update giocatore set numero_ammonizioni = numero_ammonizioni + 1 where id = ?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
            databaseConfig.getCon().commit();
        } catch (SQLException e) {
            if (databaseConfig.getCon() != null) {
                databaseConfig.getCon().rollback();
            }
            throw new RuntimeException(e);
        }
    }
}

