package it.reactive.academy.springMvc.repository.statement;

import it.reactive.academy.springMvc.utility.Costanti;
import it.reactive.academy.springMvc.dto.extended.GiocatoreDTOExtended;
import it.reactive.academy.springMvc.dto.extended.SquadraDTOExtended;
import it.reactive.academy.springMvc.mapper.GiocatoreMapper;
import it.reactive.academy.springMvc.mapper.SquadraMapper;
import it.reactive.academy.springMvc.model.GiocatoreModel;
import it.reactive.academy.springMvc.model.SquadraModel;
import it.reactive.academy.springMvc.repository.dao.GiocatoreDao;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
@Profile(Costanti.TORNEO_DAO_JDBC_STATEMENT)
public class GiocatoreDaoImpl implements GiocatoreDao {
    private final PlatformTransactionManager transactionManager;

    public GiocatoreDaoImpl(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    @Override
    public GiocatoreDTOExtended create(GiocatoreDTOExtended giocatoreDTOExtended) throws SQLException {
        GiocatoreModel giocatoreModel = GiocatoreMapper.giocatoreDtoExtendedToModel(giocatoreDTOExtended);

        try (Connection con = DataSourceUtils.getConnection(Objects.requireNonNull(((DataSourceTransactionManager) transactionManager).getDataSource()));
             Statement statement = con.createStatement()) {

            String s = "insert into giocatore (nome_cognome,id_squadra) values ('" +
                    giocatoreModel.getNomeCognome() + "', " +
                    giocatoreModel.getSquadra().getIdSquadra() + ")";
            statement.executeUpdate(s, Statement.RETURN_GENERATED_KEYS);

            try (ResultSet rs = statement.getGeneratedKeys()) {
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

        try (Connection con = DataSourceUtils.getConnection(Objects.requireNonNull(((DataSourceTransactionManager) transactionManager).getDataSource()));
             Statement statement = con.createStatement()) {

            for (GiocatoreModel giocatoreModel : giocatoriModel) {
                String s = "insert into giocatore (nome_cognome, id_squadra) values ('" +
                        giocatoreModel.getNomeCognome() + "', " +
                        giocatoreModel.getSquadra().getIdSquadra() + ")";
                statement.executeUpdate(s, Statement.RETURN_GENERATED_KEYS);


                try (ResultSet rs = statement.getGeneratedKeys()) {
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

        try (Connection con = DataSourceUtils.getConnection(Objects.requireNonNull(((DataSourceTransactionManager) transactionManager).getDataSource()));
             Statement statement = con.createStatement()) {

            String s = "select * from giocatore where id_squadra = "
                    + squadraModel.getIdSquadra();
            try (ResultSet resultGiocatori = statement.executeQuery(s)) {
                while (resultGiocatori.next()) {
                    GiocatoreModel giocatoreModel = new GiocatoreModel();
                    giocatoreModel.setIdGiocatore(resultGiocatori.getInt("id"));
                    giocatoreModel.setNomeCognome(resultGiocatori.getString("nome_cognome"));
                    giocatoreModel.setNumeroAmmonizioni(resultGiocatori.getInt("numero_ammonizioni"));
                    giocatoreModel.setSquadra(squadraModel);
                    listaGiocatori.add(GiocatoreMapper.giocatoreModelToDTOExtended(giocatoreModel));
                }
            }
        }

        return listaGiocatori;
    }

    public boolean checkByName(String nomeGiocatore) throws SQLException {
        try (Connection con = DataSourceUtils.getConnection(Objects.requireNonNull(((DataSourceTransactionManager) transactionManager).getDataSource()));
             Statement statement = con.createStatement()) {

            String s = "select * from squadra where nome = '" + nomeGiocatore + "'";
            try (ResultSet rs = statement.executeQuery(s)) {
                return rs.next();
            }
        }
    }

    @Override
    public GiocatoreDTOExtended findGiocatoreById(Integer id) throws SQLException {
        GiocatoreModel giocatoreModel = new GiocatoreModel();

        try (Connection con = DataSourceUtils.getConnection(Objects.requireNonNull(((DataSourceTransactionManager) transactionManager).getDataSource()));
             Statement statement = con.createStatement()) {

            String s = "select * from giocatore where id = " + id;
            try (ResultSet rs = statement.executeQuery(s)) {
                if (rs.next()) {
                    giocatoreModel.setIdGiocatore(rs.getInt("id"));
                    giocatoreModel.setNomeCognome(rs.getString("nome_cognome"));
                    giocatoreModel.setNumeroAmmonizioni(rs.getInt("numero_ammonizioni"));
                }
            }
        }

        return GiocatoreMapper.giocatoreModelToDTOExtended(giocatoreModel);
    }

    @Override
    public void updateAmmonizioni(Integer id) throws SQLException {
        try (Connection con = DataSourceUtils.getConnection(Objects.requireNonNull(((DataSourceTransactionManager) transactionManager).getDataSource()));
             Statement statement = con.createStatement()) {

            String s = "update giocatore set numero_ammonizioni = numero_ammonizioni +1" +
                    " where id = " + id;
            statement.executeUpdate(s);
        }
    }
}
