package it.reactive.academy.springMvc.repository.preparedstatement;

import it.reactive.academy.springMvc.utility.Costanti;
import it.reactive.academy.springMvc.dto.extended.GiocatoreDTOExtended;
import it.reactive.academy.springMvc.dto.extended.SquadraDTOExtended;
import it.reactive.academy.springMvc.utility.mapper.GiocatoreMapper;
import it.reactive.academy.springMvc.utility.mapper.SquadraMapper;
import it.reactive.academy.springMvc.entity.GiocatoreEntity;
import it.reactive.academy.springMvc.entity.SquadraEntity;
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
        GiocatoreEntity giocatoreEntity = GiocatoreMapper.giocatoreDtoExtendedToEntity(giocatoreDTOExtended);

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DataSourceUtils.getConnection(Objects.requireNonNull(((DataSourceTransactionManager) transactionManager).getDataSource()));
            ps = con.prepareStatement(
                    "insert into giocatore (nome_cognome, id_squadra) values (?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, giocatoreEntity.getNomeCognome());
            ps.setInt(2, giocatoreEntity.getSquadra().getIdSquadra());
            ps.executeUpdate();

            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                giocatoreEntity.setIdGiocatore(rs.getInt(1));
            }
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (con != null) {
                DataSourceUtils.releaseConnection(con, ((DataSourceTransactionManager) transactionManager).getDataSource());
            }
        }

        return GiocatoreMapper.giocatoreEntityToDTOExtended(giocatoreEntity);
    }

    @Override
    public Set<GiocatoreDTOExtended> createAll(Set<GiocatoreDTOExtended> giocatoriDTOExtended) throws SQLException {
        Set<GiocatoreEntity> giocatoriModel = giocatoriDTOExtended.stream()
                .map(GiocatoreMapper::giocatoreDtoExtendedToEntity)
                .collect(Collectors.toSet());
        Set<GiocatoreEntity> giocatoriResult = new HashSet<>();

        Connection con = null;
        PreparedStatement psInsert = null;
        ResultSet rs = null;

        try {
            con = DataSourceUtils.getConnection(Objects.requireNonNull(((DataSourceTransactionManager) transactionManager).getDataSource()));
            psInsert = con.prepareStatement(
                    "insert into giocatore (nome_cognome, id_squadra) values (?, ?)", Statement.RETURN_GENERATED_KEYS);

            for (GiocatoreEntity giocatoreEntity : giocatoriModel) {
                psInsert.setString(1, giocatoreEntity.getNomeCognome());
                psInsert.setInt(2, giocatoreEntity.getSquadra().getIdSquadra());
                psInsert.executeUpdate();

                rs = psInsert.getGeneratedKeys();
                if (rs.next()) {
                    giocatoreEntity.setIdGiocatore(rs.getInt(1));
                    giocatoriResult.add(giocatoreEntity);
                }
            }
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (psInsert != null) {
                try {
                    psInsert.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (con != null) {
                DataSourceUtils.releaseConnection(con, ((DataSourceTransactionManager) transactionManager).getDataSource());
            }
        }
        return giocatoriResult.stream()
                .map(GiocatoreMapper::giocatoreEntityToDTOExtended)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<GiocatoreDTOExtended> readAllByTeam(SquadraDTOExtended squadraDTOExtended) throws SQLException {
        Set<GiocatoreDTOExtended> listaGiocatori = new HashSet<>();
        SquadraEntity squadraEntity = SquadraMapper.squadraDtoExtendedToEntity(squadraDTOExtended);

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet resultGiocatori = null;

        try {
            con = DataSourceUtils.getConnection(Objects.requireNonNull(((DataSourceTransactionManager) transactionManager).getDataSource()));
            ps = con.prepareStatement(
                    "select * from giocatore where id_squadra = ?");
            ps.setInt(1, squadraEntity.getIdSquadra());
            resultGiocatori = ps.executeQuery();

            while (resultGiocatori.next()) {
                GiocatoreEntity giocatoreEntity = new GiocatoreEntity();
                giocatoreEntity.setIdGiocatore(resultGiocatori.getInt("id"));
                giocatoreEntity.setNomeCognome(resultGiocatori.getString("nome_cognome"));
                giocatoreEntity.setNumeroAmmonizioni(resultGiocatori.getInt("numero_ammonizioni"));
                giocatoreEntity.setSquadra(squadraEntity);
                listaGiocatori.add(GiocatoreMapper.giocatoreEntityToDTOExtended(giocatoreEntity));
            }
        } finally {
            if (resultGiocatori != null) {
                try {
                    resultGiocatori.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (con != null) {
                DataSourceUtils.releaseConnection(con, ((DataSourceTransactionManager) transactionManager).getDataSource());
            }
        }
        return listaGiocatori;
    }

    public boolean checkByName(String nomeGiocatore) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DataSourceUtils.getConnection(Objects.requireNonNull(((DataSourceTransactionManager) transactionManager).getDataSource()));
            ps = con.prepareStatement(
                    "select id from giocatore where nome_cognome = ?");
            ps.setString(1, nomeGiocatore);
            rs = ps.executeQuery();
            return rs.next();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
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
    public GiocatoreDTOExtended findGiocatoreById(Integer id) throws SQLException {
        GiocatoreEntity giocatoreEntity = new GiocatoreEntity();

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DataSourceUtils.getConnection(Objects.requireNonNull(((DataSourceTransactionManager) transactionManager).getDataSource()));
            ps = con.prepareStatement(
                    "select id, nome_cognome, numero_ammonizioni from giocatore where id = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                giocatoreEntity.setIdGiocatore(rs.getInt("id"));
                giocatoreEntity.setNomeCognome(rs.getString("nome_cognome"));
                giocatoreEntity.setNumeroAmmonizioni(rs.getInt("numero_ammonizioni"));
            }
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (con != null) {
                DataSourceUtils.releaseConnection(con, ((DataSourceTransactionManager) transactionManager).getDataSource());
            }
        }

        return GiocatoreMapper.giocatoreEntityToDTOExtended(giocatoreEntity);
    }

    @Override
    public void updateAmmonizioni(Integer id) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DataSourceUtils.getConnection(Objects.requireNonNull(((DataSourceTransactionManager) transactionManager).getDataSource()));
            ps = con.prepareStatement(
                    "update giocatore set numero_ammonizioni = numero_ammonizioni + 1 where id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
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
