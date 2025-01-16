package it.reactive.academy.springMvc.repository.statement;

import it.reactive.academy.springMvc.utility.Costanti;
import it.reactive.academy.springMvc.dto.extended.SquadraDTOExtended;
import it.reactive.academy.springMvc.dto.extended.TifoseriaDTOExtended;
import it.reactive.academy.springMvc.utility.mapper.SquadraMapper;
import it.reactive.academy.springMvc.utility.mapper.TifoseriaMapper;
import it.reactive.academy.springMvc.entity.SquadraEntity;
import it.reactive.academy.springMvc.entity.TifoseriaEntity;
import it.reactive.academy.springMvc.repository.dao.TifoseriaDao;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.transaction.PlatformTransactionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

@Repository
@Profile(Costanti.TORNEO_DAO_JDBC_STATEMENT)
public class TifoseriaDaoImpl implements TifoseriaDao {

    private final PlatformTransactionManager transactionManager;

    public TifoseriaDaoImpl(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    @Override
    public TifoseriaDTOExtended createWithTeam(TifoseriaDTOExtended tifoseriaDTOExtended, Integer idSquadra) throws SQLException {
        TifoseriaEntity tifoseriaEntity = TifoseriaMapper.tifoseriaDtoExtendedToEntity(tifoseriaDTOExtended);
        Connection con = null;
        Statement statement = null;
        ResultSet rs = null;

        try {
            con = DataSourceUtils.getConnection(Objects.requireNonNull(((DataSourceTransactionManager) transactionManager).getDataSource()));
            statement = con.createStatement();

            String s = "insert into tifoseria (nome_tifoseria, id_squadra) values('" +
                    tifoseriaEntity.getNomeTifoseria() + "', " +
                    idSquadra + ")";
            statement.executeUpdate(s, Statement.RETURN_GENERATED_KEYS);

            rs = statement.getGeneratedKeys();
            if (rs.next()) {
                tifoseriaEntity.setIdTifoseria(rs.getInt(1));
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

        return TifoseriaMapper.tifoseriaEntityToDtoExtended(tifoseriaEntity);
    }

    public TifoseriaDTOExtended readByTeam(SquadraDTOExtended squadraDTOExtended) throws SQLException {
        TifoseriaEntity tifoseriaEntity = new TifoseriaEntity();
        SquadraEntity squadraEntity = SquadraMapper.squadraDtoExtendedToEntity(squadraDTOExtended);
        Connection con = null;
        Statement statement = null;
        ResultSet rs = null;

        try {
            con = DataSourceUtils.getConnection(Objects.requireNonNull(((DataSourceTransactionManager) transactionManager).getDataSource()));
            statement = con.createStatement();

            String s = "select * from tifoseria where id_squadra = " + squadraEntity.getIdSquadra();
            rs = statement.executeQuery(s);
            if (rs.next()) {
                tifoseriaEntity.setIdTifoseria(rs.getInt("id"));
                tifoseriaEntity.setNomeTifoseria(rs.getString("nome_tifoseria"));
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

        return TifoseriaMapper.tifoseriaEntityToDtoExtended(tifoseriaEntity);
    }

    @Override
    public TifoseriaDTOExtended updateName(String name, Integer idSquadra) throws SQLException {
        TifoseriaEntity tifoseriaEntity = new TifoseriaEntity();
        Connection con = null;
        Statement statement = null;
        ResultSet rs = null;

        try {
            con = DataSourceUtils.getConnection(Objects.requireNonNull(((DataSourceTransactionManager) transactionManager).getDataSource()));
            statement = con.createStatement();

            String s = "update tifoseria set nome_tifoseria = '" +
                    name + "' where id_squadra = " + idSquadra;
            statement.executeUpdate(s);

            rs = statement.executeQuery("select * from tifoseria where nome_tifoseria = '" +
                    name + "'");
            if (rs.next()) {
                tifoseriaEntity.setIdTifoseria(rs.getInt("id"));
                tifoseriaEntity.setNomeTifoseria(rs.getString("nome_tifoseria"));
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

        return TifoseriaMapper.tifoseriaEntityToDtoExtended(tifoseriaEntity);
    }
}
