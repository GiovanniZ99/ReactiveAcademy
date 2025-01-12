package it.reactive.academy.springMvc.repository.statement;

import it.reactive.academy.springMvc.utility.Costanti;
import it.reactive.academy.springMvc.dto.extended.SquadraDTOExtended;
import it.reactive.academy.springMvc.utility.mapper.SquadraMapper;
import it.reactive.academy.springMvc.model.SquadraModel;
import it.reactive.academy.springMvc.repository.dao.SquadraDao;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.transaction.PlatformTransactionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Repository
@Profile(Costanti.TORNEO_DAO_JDBC_STATEMENT)
public class SquadraDaoImpl implements SquadraDao {

    private final PlatformTransactionManager transactionManager;

    public SquadraDaoImpl(PlatformTransactionManager transactionManager) {

        this.transactionManager = transactionManager;
    }

    @Override
    public SquadraDTOExtended create(SquadraDTOExtended squadraDTOExtended) throws SQLException {
        SquadraModel squadraModel = SquadraMapper.squadraDtoExtendedToModel(squadraDTOExtended);

        try (Connection con = DataSourceUtils.getConnection(Objects.requireNonNull(((DataSourceTransactionManager) transactionManager).getDataSource()));
             Statement statement = con.createStatement()) {
            String s = "insert into squadra (nome, colori_sociali) values ('"
                    + squadraModel.getNome() + "', '" + squadraModel.getColoriSociali() + "')";

            statement.executeUpdate(s, Statement.RETURN_GENERATED_KEYS);

            try (ResultSet rs = statement.getGeneratedKeys()) {
                if (rs.next()) {
                    squadraModel.setIdSquadra(rs.getInt(1));
                }
            }
        }

        SquadraDTOExtended squadraResult = SquadraMapper.squadraModelToDtoExtendended(squadraModel);
        squadraResult.setGiocatori(squadraDTOExtended.getGiocatori());
        return squadraResult;
    }

    @Override
    public List<SquadraDTOExtended> readAll() throws SQLException {
        List<SquadraDTOExtended> listaSquadra = new LinkedList<>();

        try (Connection con = DataSourceUtils.getConnection(Objects.requireNonNull(((DataSourceTransactionManager) transactionManager).getDataSource()));
             Statement statement = con.createStatement()) {

            String s = "select * from squadra";
            try (ResultSet rs = statement.executeQuery(s)) {
                while (rs.next()) {
                    SquadraModel squadraModel = new SquadraModel();
                    squadraModel.setIdSquadra(rs.getInt(1));
                    squadraModel.setNome(rs.getString(2));
                    squadraModel.setColoriSociali(rs.getString(3));
                    listaSquadra.add(SquadraMapper.squadraModelToDtoExtendended(squadraModel));
                }
            }
        }

        return listaSquadra;
    }

    @Override
    public SquadraDTOExtended findSquadraByOd(Integer idSquadra) throws SQLException {
        SquadraModel squadraModel = new SquadraModel();

        try (Connection con = DataSourceUtils.getConnection(Objects.requireNonNull(((DataSourceTransactionManager) transactionManager).getDataSource()));
             Statement statement = con.createStatement()) {

            String s = "select * from squadra where id = " + idSquadra;
            try (ResultSet rs = statement.executeQuery(s)) {
                if (rs.next()) {
                    squadraModel.setIdSquadra(rs.getInt("id"));
                    squadraModel.setNome(rs.getString("nome"));
                    squadraModel.setColoriSociali(rs.getString("colori_sociali"));
                }
            }
        }

        return SquadraMapper.squadraModelToDtoExtendended(squadraModel);
    }

    @Override
    public boolean checkSquadraByName(String nomeSquadra) throws SQLException {
        try (Connection con = DataSourceUtils.getConnection(Objects.requireNonNull(((DataSourceTransactionManager) transactionManager).getDataSource()));
             Statement statement = con.createStatement()) {

            String s = "select * from squadra where nome = '" + nomeSquadra + "'";
            try (ResultSet rs = statement.executeQuery(s)) {
                return rs.next();
            }
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
        try (Connection con = DataSourceUtils.getConnection(Objects.requireNonNull(((DataSourceTransactionManager) transactionManager).getDataSource()));
             Statement statement = con.createStatement()) {

            String s = "delete from squadra_torneo where id_squadra =" + id;
            statement.executeUpdate(s);
            String s1 = "delete from tifoseria where id_squadra = " + id;
            statement.executeUpdate(s1);
            String s2 = "delete from giocatore where id_squadra = " + id;
            statement.executeUpdate(s2);
            String s3 = "delete from squadra where id = " + id;
            statement.executeUpdate(s3);
        }
    }
}
