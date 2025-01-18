package it.reactive.academy.springMvc.repository.statement;

import it.reactive.academy.springMvc.utility.Costanti;
import it.reactive.academy.springMvc.dto.extended.TorneoDTOExtended;
import it.reactive.academy.springMvc.utility.mapper.TorneoMapper;
import it.reactive.academy.springMvc.entity.TorneoEntity;
import it.reactive.academy.springMvc.repository.dao.TorneoDao;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;

import java.sql.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
@Profile(Costanti.TORNEO_DAO_JDBC_STATEMENT)
public class TorneoDaoImpl implements TorneoDao {

    private final PlatformTransactionManager transactionManager;

    public TorneoDaoImpl(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    @Override
    public TorneoDTOExtended create(String nomeTorneo) throws SQLException {
        TorneoEntity torneoEntity = new TorneoEntity();
        torneoEntity.setNomeTorneo(nomeTorneo);

        ResultSet rs = null;
        Connection con = null;
        Statement statement = null;

        try {
            con = DataSourceUtils.getConnection(Objects.requireNonNull(((DataSourceTransactionManager) transactionManager).getDataSource()));
            statement = con.createStatement();
            String s = "insert into torneo (nome_torneo) values('" + nomeTorneo + "')";
            statement.executeUpdate(s, Statement.RETURN_GENERATED_KEYS);
            rs = statement.getGeneratedKeys();

            if (rs.next()) {
                torneoEntity.setIdTorneo(rs.getInt(1));
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

        return TorneoMapper.torneoEntityToDtoExtended(torneoEntity);
    }

    @Override
    public TorneoDTOExtended findById(Integer idTorneo) throws SQLException {
        TorneoEntity torneoEntity = new TorneoEntity();
        ResultSet rs = null;
        Connection con = null;
        Statement statement = null;

        try {
            con = DataSourceUtils.getConnection(Objects.requireNonNull(((DataSourceTransactionManager) transactionManager).getDataSource()));
            statement = con.createStatement();
            String s = "select * from torneo where id = " + idTorneo;
            rs = statement.executeQuery(s);

            if (rs.next()) {
                torneoEntity.setIdTorneo(rs.getInt(1));
                torneoEntity.setNomeTorneo(rs.getString(2));
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

        return TorneoMapper.torneoEntityToDtoExtended(torneoEntity);
    }

    @Override
    public Set<TorneoDTOExtended> findAll() throws SQLException {
        Set<TorneoEntity> tornei = new HashSet<>();
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            con = DataSourceUtils.getConnection(Objects.requireNonNull(((DataSourceTransactionManager) transactionManager).getDataSource()));
            st = con.createStatement();
            rs = st.executeQuery("select * from torneo");
            while (rs.next()) {
                TorneoEntity torneo = new TorneoEntity();
                torneo.setIdTorneo(rs.getInt("id"));
                torneo.setNomeTorneo(rs.getString("nome_torneo"));
                tornei.add(torneo);
            }
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (con != null) {
                DataSourceUtils.releaseConnection(con, ((DataSourceTransactionManager) transactionManager).getDataSource());
            }
        }
            return tornei.stream().map(TorneoMapper::torneoEntityToDtoExtended).collect(Collectors.toSet());
        }

        @Override
        public void delete (Integer id) throws SQLException {
            Connection con = null;
            Statement statement = null;

            try {
                con = DataSourceUtils.getConnection(Objects.requireNonNull(((DataSourceTransactionManager) transactionManager).getDataSource()));
                statement = con.createStatement();
                String s = "delete from squadra_torneo where id_torneo =" + id;
                statement.executeUpdate(s);
                String s1 = "delete from torneo where id = " + id;
                statement.executeUpdate(s1);
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
