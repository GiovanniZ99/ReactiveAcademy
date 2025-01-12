package it.reactive.academy.springMvc.repository.preparedstatement;

import it.reactive.academy.springMvc.utility.Costanti;
import it.reactive.academy.springMvc.dto.extended.GiocatoreDTOExtended;
import it.reactive.academy.springMvc.dto.extended.SquadraDTOExtended;
import it.reactive.academy.springMvc.utility.mapper.GiocatoreMapper;
import it.reactive.academy.springMvc.utility.mapper.SquadraMapper;
import it.reactive.academy.springMvc.model.GiocatoreModel;
import it.reactive.academy.springMvc.model.SquadraModel;
import it.reactive.academy.springMvc.repository.dao.GiocatoreDao;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;

import java.sql.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
@Profile(Costanti.TORNEO_DAO_JDBC_PREPAREDSTATEMENT)
public class GiocatoreDaoImpl implements GiocatoreDao {

    private final PlatformTransactionManager transactionManager;

    public GiocatoreDaoImpl(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    @Override
    public GiocatoreDTOExtended create(GiocatoreDTOExtended giocatoreDTOExtended) throws SQLException {
        GiocatoreModel giocatoreModel = GiocatoreMapper.giocatoreDtoExtendedToModel(giocatoreDTOExtended);

        Connection con = DataSourceUtils.getConnection(Objects.requireNonNull(((DataSourceTransactionManager) transactionManager).getDataSource()));
        try (PreparedStatement ps = con.prepareStatement(
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
            }

        return GiocatoreMapper.giocatoreModelToDTOExtended(giocatoreModel);
    }

    @Override
    public Set<GiocatoreDTOExtended> createAll(Set<GiocatoreDTOExtended> giocatoriDTOExtended) throws SQLException {
        Set<GiocatoreModel> giocatoriModel = giocatoriDTOExtended.stream()
                .map(GiocatoreMapper::giocatoreDtoExtendedToModel)
                .collect(Collectors.toSet());
        Set<GiocatoreModel> giocatoriResult = new HashSet<>();

        Connection con = DataSourceUtils.getConnection(Objects.requireNonNull(((DataSourceTransactionManager) transactionManager).getDataSource()));

        try (PreparedStatement psInsert = con.prepareStatement(
                "insert into giocatore (nome_cognome, id_squadra) values (?, ?)", Statement.RETURN_GENERATED_KEYS)) {

            for (GiocatoreModel giocatoreModel : giocatoriModel) {
                psInsert.setString(1, giocatoreModel.getNomeCognome());
                psInsert.setInt(2, giocatoreModel.getSquadra().getIdSquadra());
                psInsert.executeUpdate();

                try (ResultSet rs = psInsert.getGeneratedKeys()) {
                    if (rs.next()) {
                        giocatoreModel.setIdGiocatore(rs.getInt(1));
                        giocatoriResult.add(giocatoreModel);
                    }
                }
            }
        }
        return giocatoriResult.stream()
                .map(GiocatoreMapper::giocatoreModelToDTOExtended)
                .collect(Collectors.toSet());
    }


    @Override
    public Set<GiocatoreDTOExtended> readAllByTeam(SquadraDTOExtended squadraDTOExtended) throws SQLException {
        Set<GiocatoreDTOExtended> listaGiocatori = new HashSet<>();
        SquadraModel squadraModel = SquadraMapper.squadraDtoExtendedToModel(squadraDTOExtended);

        Connection con = DataSourceUtils.getConnection(Objects.requireNonNull(((DataSourceTransactionManager) transactionManager).getDataSource()));
        try (PreparedStatement ps = con.prepareStatement(
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
        }

        return listaGiocatori;
    }

    public boolean checkByName(String nomeGiocatore) throws SQLException {
        Connection con = DataSourceUtils.getConnection(Objects.requireNonNull(((DataSourceTransactionManager) transactionManager).getDataSource()));

        try (PreparedStatement ps = con.prepareStatement(
                "select id from giocatore where nome_cognome = ?")) {
            ps.setString(1, nomeGiocatore);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        }
    }

    @Override
    public GiocatoreDTOExtended findGiocatoreById(Integer id) throws SQLException {
        GiocatoreModel giocatoreModel = new GiocatoreModel();

        Connection con = DataSourceUtils.getConnection(Objects.requireNonNull(((DataSourceTransactionManager) transactionManager).getDataSource()));
        try (PreparedStatement ps = con.prepareStatement(
                "select id, nome_cognome, numero_ammonizioni from giocatore where id = ?")) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                giocatoreModel.setIdGiocatore(rs.getInt("id"));
                giocatoreModel.setNomeCognome(rs.getString("nome_cognome"));
                giocatoreModel.setNumeroAmmonizioni(rs.getInt("numero_ammonizioni"));
            }
        }

        return GiocatoreMapper.giocatoreModelToDTOExtended(giocatoreModel);
    }

    @Override
    public void updateAmmonizioni(Integer id) throws SQLException {
        Connection con = DataSourceUtils.getConnection(Objects.requireNonNull(((DataSourceTransactionManager) transactionManager).getDataSource()));
        try (PreparedStatement ps = con.prepareStatement(
                "update giocatore set numero_ammonizioni = numero_ammonizioni + 1 where id = ?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}

