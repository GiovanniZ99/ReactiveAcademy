package it.reactive.academy.springMvc.repository.statement;

import it.reactive.academy.springMvc.utility.Costanti;
import it.reactive.academy.springMvc.dto.extended.SquadraDTOExtended;
import it.reactive.academy.springMvc.dto.extended.SquadraTorneoDTOExtended;
import it.reactive.academy.springMvc.dto.extended.TorneoDTOExtended;
import it.reactive.academy.springMvc.utility.mapper.*;
import it.reactive.academy.springMvc.entity.*;
import it.reactive.academy.springMvc.repository.dao.SquadraTorneoDao;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.transaction.PlatformTransactionManager;

import java.sql.*;
import java.util.*;

@Repository
@Profile(Costanti.TORNEO_DAO_JDBC_STATEMENT)
public class SquadraTorneoDaoImpl implements SquadraTorneoDao {

    private final PlatformTransactionManager transactionManager;

    public SquadraTorneoDaoImpl(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    @Override
    public SquadraTorneoDTOExtended create(TorneoDTOExtended torneoDTOExtended, SquadraDTOExtended squadraDTOExtended) throws SQLException {
        SquadraTorneoEntity squadraTorneoEntity = new SquadraTorneoEntity();
        TorneoEntity torneo = TorneoMapper.torneoDTOExtendedToEntity(torneoDTOExtended);
        SquadraEntity squadra = SquadraMapper.squadraDtoExtendedToEntity(squadraDTOExtended);
        squadraTorneoEntity.setTorneoEntity(torneo);
        squadraTorneoEntity.setSquadraEntity(squadra);

        Connection con = null;
        Statement statement = null;

        try {
            con = DataSourceUtils.getConnection(Objects.requireNonNull(((DataSourceTransactionManager) transactionManager).getDataSource()));
            statement = con.createStatement();

            String s = "insert into squadra_torneo (id_squadra, id_torneo) values(" +
                    squadraDTOExtended.getIdSquadra() + "," + torneoDTOExtended.getIdTorneo() + ")";
            statement.executeUpdate(s);
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

        return SquadraTorneoMapper.squadraEntityToDtoExtended(squadraTorneoEntity);
    }

    @Override
    public Set<Integer> readAllTeamsById(Integer idTorneo) throws SQLException {
        Set<Integer> setIdSquadre = new HashSet<>();
        Connection con = null;
        Statement statement = null;
        ResultSet rs = null;

        try {
            con = DataSourceUtils.getConnection(Objects.requireNonNull(((DataSourceTransactionManager) transactionManager).getDataSource()));
            statement = con.createStatement();

            String s = "select id_squadra from squadra_torneo where id_torneo = " + idTorneo;
            rs = statement.executeQuery(s);

            while (rs.next()) {
                setIdSquadre.add(rs.getInt(1));
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

        return setIdSquadre;
    }

    @Override
    public LinkedHashMap<Integer, Set<Integer>> readAllTornei() throws SQLException {
        LinkedHashMap<Integer, Set<Integer>> mappaId = new LinkedHashMap<>();
        String s = "SELECT id_squadra, id_torneo FROM squadra_torneo ORDER BY id_torneo";
        Connection con = DataSourceUtils.getConnection(Objects.requireNonNull(((DataSourceTransactionManager) transactionManager).getDataSource()));
        Statement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(s);

            while (rs.next()) {
                Integer idTorneo = rs.getInt("id_torneo");
                Integer idSquadra = rs.getInt("id_squadra");
                mappaId.computeIfAbsent(idTorneo, k -> new HashSet<>()).add(idSquadra);
            }
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
                DataSourceUtils.releaseConnection(con, ((DataSourceTransactionManager) transactionManager).getDataSource());
        }

        return mappaId;
    }

    @Override
    public Set<Integer> readAllTorneoByIdSquadra(Integer idSquadra) throws SQLException {
        Set<Integer> listaIdTornei = new HashSet<>();
        String s = "SELECT id_squadra, id_torneo FROM squadra_torneo WHERE id_squadra = " + idSquadra;
        Connection con = DataSourceUtils.getConnection(Objects.requireNonNull(((DataSourceTransactionManager) transactionManager).getDataSource()));
        Statement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(s);

            while (rs.next()) {
                listaIdTornei.add(rs.getInt(1));
            }
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

                DataSourceUtils.releaseConnection(con, ((DataSourceTransactionManager) transactionManager).getDataSource());

        }

        return listaIdTornei;
    }

    @Deprecated
    @Override
    public Set<TorneoDTOExtended> readAllTorneo() throws SQLException {
        Set<TorneoDTOExtended> tornei = new HashSet<>();
        SquadraDTOExtended squadraDTOExtended = new SquadraDTOExtended();
        squadraDTOExtended.setGiocatori(new HashSet<>());
        TorneoDTOExtended torneoDTOExtended = new TorneoDTOExtended();
        torneoDTOExtended.setSquadre(new HashSet<>());

        Connection con = DataSourceUtils.getConnection(Objects.requireNonNull(((DataSourceTransactionManager) transactionManager).getDataSource()));
        Statement statement = null;
        ResultSet rs = null;
        String s = "select * from torneo t " +
                "join squadra_torneo st on t.id = st.id_torneo " +
                "join squadra s on st.id_squadra = s.id " +
                "join giocatore g on s.id = g.id_squadra " +
                "join tifoseria ti on s.id = ti.id_squadra " +
                "order by t.id, s.id";

        try {
            statement = con.createStatement();
            rs = statement.executeQuery(s);

            while (rs.next()) {
                TorneoEntity torneoEntity = new TorneoEntity();
                SquadraEntity squadraEntity = new SquadraEntity();
                GiocatoreEntity giocatoreEntity = new GiocatoreEntity();
                TifoseriaEntity tifoseriaEntity = new TifoseriaEntity();

                torneoEntity.setIdTorneo(rs.getInt(1));
                torneoEntity.setNomeTorneo(rs.getString(2));

                squadraEntity.setIdSquadra(rs.getInt(3));
                squadraEntity.setNome(rs.getString(6));
                squadraEntity.setColoriSociali(rs.getString(7));

                tifoseriaEntity.setIdTifoseria(rs.getInt(12));
                tifoseriaEntity.setNomeTifoseria(rs.getString(13));

                giocatoreEntity.setIdGiocatore(rs.getInt(8));
                giocatoreEntity.setNomeCognome(rs.getString(9));
                giocatoreEntity.setNumeroAmmonizioni(rs.getInt(10));

                if (!Objects.equals(squadraDTOExtended.getIdSquadra(), squadraEntity.getIdSquadra())) {
                    squadraDTOExtended = new SquadraDTOExtended();
                    squadraDTOExtended.setGiocatori(new HashSet<>());
                }
                squadraDTOExtended.setIdSquadra(squadraEntity.getIdSquadra());
                squadraDTOExtended.setNome(squadraEntity.getNome());
                squadraDTOExtended.setColoriSociali(squadraEntity.getColoriSociali());
                squadraDTOExtended.getGiocatori().add(GiocatoreMapper.giocatoreEntityToDTOExtended(giocatoreEntity));
                squadraDTOExtended.setTifoseria(TifoseriaMapper.tifoseriaEntityToDtoExtended(tifoseriaEntity));

                if (!Objects.equals(torneoDTOExtended.getIdTorneo(), torneoEntity.getIdTorneo())) {
                    torneoDTOExtended = new TorneoDTOExtended();
                    torneoDTOExtended.setSquadre(new HashSet<>());
                }
                torneoDTOExtended.setIdTorneo(torneoEntity.getIdTorneo());
                torneoDTOExtended.getSquadre().add(squadraDTOExtended);
                torneoDTOExtended.setNomeTorneo(torneoEntity.getNomeTorneo());
                tornei.add(torneoDTOExtended);
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
            DataSourceUtils.releaseConnection(con, ((DataSourceTransactionManager) transactionManager).getDataSource());

        }

        return tornei;
    }
}
