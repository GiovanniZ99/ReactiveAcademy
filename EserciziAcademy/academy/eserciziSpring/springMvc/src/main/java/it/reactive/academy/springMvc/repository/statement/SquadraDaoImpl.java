package it.reactive.academy.springMvc.repository.statement;

import it.reactive.academy.springMvc.utility.Costanti;
import it.reactive.academy.springMvc.dto.extended.SquadraDTOExtended;
import it.reactive.academy.springMvc.utility.mapper.SquadraMapper;
import it.reactive.academy.springMvc.entity.SquadraEntity;
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
        SquadraEntity squadraEntity = SquadraMapper.squadraDtoExtendedToEntity(squadraDTOExtended);
        Connection con = null;
        Statement statement = null;
        ResultSet rs = null;

        try {
            con = DataSourceUtils.getConnection(Objects.requireNonNull(((DataSourceTransactionManager) transactionManager).getDataSource()));
            statement = con.createStatement();
            String s = "insert into squadra (nome, colori_sociali) values ('"
                    + squadraEntity.getNome() + "', '" + squadraEntity.getColoriSociali() + "')";
            statement.executeUpdate(s, Statement.RETURN_GENERATED_KEYS);

            rs = statement.getGeneratedKeys();
            if (rs.next()) {
                squadraEntity.setIdSquadra(rs.getInt(1));
            }
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (con != null) {
                DataSourceUtils.releaseConnection(con, ((DataSourceTransactionManager) transactionManager).getDataSource());
            }
        }

        SquadraDTOExtended squadraResult = SquadraMapper.squadraEntityToDtoExtendended(squadraEntity);
        squadraResult.setGiocatori(squadraDTOExtended.getGiocatori());
        return squadraResult;
    }

    @Override
    public List<SquadraDTOExtended> readAll() throws SQLException {
        List<SquadraDTOExtended> listaSquadra = new LinkedList<>();
        Connection con = null;
        Statement statement = null;
        ResultSet rs = null;

        try {
            con = DataSourceUtils.getConnection(Objects.requireNonNull(((DataSourceTransactionManager) transactionManager).getDataSource()));
            statement = con.createStatement();
            String s = "select * from squadra";
            rs = statement.executeQuery(s);

            while (rs.next()) {
                SquadraEntity squadraEntity = new SquadraEntity();
                squadraEntity.setIdSquadra(rs.getInt(1));
                squadraEntity.setNome(rs.getString(2));
                squadraEntity.setColoriSociali(rs.getString(3));
                listaSquadra.add(SquadraMapper.squadraEntityToDtoExtendended(squadraEntity));
            }
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (con != null) {
                DataSourceUtils.releaseConnection(con, ((DataSourceTransactionManager) transactionManager).getDataSource());
            }
        }

        return listaSquadra;
    }

    @Override
    public SquadraDTOExtended findSquadraById(Integer idSquadra) throws SQLException {
        SquadraEntity squadraEntity = new SquadraEntity();
        Connection con = null;
        Statement statement = null;
        ResultSet rs = null;

        try {
            con = DataSourceUtils.getConnection(Objects.requireNonNull(((DataSourceTransactionManager) transactionManager).getDataSource()));
            statement = con.createStatement();
            String s = "select * from squadra where id = " + idSquadra;
            rs = statement.executeQuery(s);

            if (rs.next()) {
                squadraEntity.setIdSquadra(rs.getInt("id"));
                squadraEntity.setNome(rs.getString("nome"));
                squadraEntity.setColoriSociali(rs.getString("colori_sociali"));
            }
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (con != null) {
                DataSourceUtils.releaseConnection(con, ((DataSourceTransactionManager) transactionManager).getDataSource());
            }
        }

        return SquadraMapper.squadraEntityToDtoExtendended(squadraEntity);
    }

    @Override
    public boolean checkSquadraByName(String nomeSquadra) throws SQLException {
        Connection con = null;
        Statement statement = null;
        ResultSet rs = null;

        try {
            con = DataSourceUtils.getConnection(Objects.requireNonNull(((DataSourceTransactionManager) transactionManager).getDataSource()));
            statement = con.createStatement();
            String s = "select * from squadra where nome = '" + nomeSquadra + "'";
            rs = statement.executeQuery(s);

            return rs.next();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (con != null) {
                DataSourceUtils.releaseConnection(con, ((DataSourceTransactionManager) transactionManager).getDataSource());
            }
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
        Connection con = null;
        Statement statement = null;

        try {
            con = DataSourceUtils.getConnection(Objects.requireNonNull(((DataSourceTransactionManager) transactionManager).getDataSource()));
            statement = con.createStatement();

            String s = "delete from squadra_torneo where id_squadra =" + id;
            statement.executeUpdate(s);
            String s1 = "delete from tifoseria where id_squadra = " + id;
            statement.executeUpdate(s1);
            String s2 = "delete from giocatore where id_squadra = " + id;
            statement.executeUpdate(s2);
            String s3 = "delete from squadra where id = " + id;
            statement.executeUpdate(s3);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (con != null) {
                DataSourceUtils.releaseConnection(con, ((DataSourceTransactionManager) transactionManager).getDataSource());
            }
        }
    }
}
