package it.reactive.academy.springMvc.repository.preparedstatement;

import it.reactive.academy.springMvc.utility.Costanti;
import it.reactive.academy.springMvc.dto.extended.SquadraDTOExtended;
import it.reactive.academy.springMvc.dto.extended.SquadraTorneoDTOExtended;
import it.reactive.academy.springMvc.dto.extended.TorneoDTOExtended;
import it.reactive.academy.springMvc.utility.mapper.*;
import it.reactive.academy.springMvc.entity.*;
import it.reactive.academy.springMvc.repository.dao.SquadraTorneoDao;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;

import java.sql.*;

import java.util.*;
import java.util.stream.Collectors;

@Repository
@Profile(Costanti.TORNEO_DAO_JDBC_PREPAREDSTATEMENT)
public class SquadraTorneoDaoImpl implements SquadraTorneoDao {

    private final PlatformTransactionManager transactionManager;

    public SquadraTorneoDaoImpl(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    @Override
    public SquadraTorneoDTOExtended create(TorneoDTOExtended torneoDTOExtended, SquadraDTOExtended squadraDTOExtended) throws SQLException {
        SquadraTorneoId squadraTorneoId = new SquadraTorneoId();
        TorneoEntity torneo = TorneoMapper.torneoDTOExtendedToEntity(torneoDTOExtended);
        SquadraEntity squadra = SquadraMapper.squadraDtoExtendedToEntity(squadraDTOExtended);
        squadraTorneoId.setIdTorneo(torneo.getIdTorneo());
        squadraTorneoId.setIdSquadra(squadra.getIdSquadra());

        SquadraTorneoEntity squadraTorneoEntity = new SquadraTorneoEntity();
        squadraTorneoEntity.setId(squadraTorneoId);
        squadraTorneoEntity.setTorneo(torneo);
        squadraTorneoEntity.setSquadra(squadra);

        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DataSourceUtils.getConnection(Objects.requireNonNull(((DataSourceTransactionManager) transactionManager).getDataSource()));
            ps = con.prepareStatement(
                    "insert into squadra_torneo (id_squadra, id_torneo) values (?, ?)");
            ps.setInt(1, squadra.getIdSquadra());
            ps.setInt(2, torneo.getIdTorneo());
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
        return SquadraTorneoMapper.squadraTorneoEntityToDtoExtended(squadraTorneoEntity);
    }

    @Override
    public Set<SquadraDTOExtended> readAllTeamsById(TorneoDTOExtended torneoDTOExtended) throws SQLException {
        TorneoEntity torneo = TorneoMapper.torneoDTOExtendedToEntity(torneoDTOExtended);
        Set<SquadraEntity> listaSquadre = new HashSet<>();

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DataSourceUtils.getConnection(Objects.requireNonNull(((DataSourceTransactionManager) transactionManager).getDataSource()));
            ps = con.prepareStatement(
                    "select id_squadra from squadra_torneo where id_torneo = ?");
            ps.setInt(1, torneo.getIdTorneo());
            rs = ps.executeQuery();
            while (rs.next()) {
                SquadraEntity squadra = new SquadraEntity();
                squadra.setIdSquadra(rs.getInt(1));
                listaSquadre.add(squadra);
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
        return listaSquadre.stream().map(SquadraMapper::squadraEntityToDtoExtendended).collect(Collectors.toSet());
    }

    @Override
    public Set<TorneoDTOExtended> readAllTorneoByIdSquadra(SquadraDTOExtended squadraDTOExtended) throws SQLException {
        SquadraEntity squadra = SquadraMapper.squadraDtoExtendedToEntity(squadraDTOExtended);
        Set<TorneoEntity> listaTornei = new HashSet<>();
        String s = "select id_squadra, id_torneo from squadra_torneo where id_squadra = ?";
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DataSourceUtils.getConnection(Objects.requireNonNull(((DataSourceTransactionManager) transactionManager).getDataSource()));
            ps = con.prepareStatement(s);
            ps.setInt(1, squadra.getIdSquadra());
            rs = ps.executeQuery();
            while (rs.next()) {
                TorneoEntity torneo = new TorneoEntity();
                torneo.setIdTorneo(rs.getInt("id_torneo"));
                listaTornei.add(torneo);
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
        return listaTornei.stream().map(TorneoMapper::torneoEntityToDtoExtended).collect(Collectors.toSet());
    }

    @Deprecated
    @Override
    public Set<TorneoDTOExtended> readAllTorneo() throws SQLException {
        Set<TorneoDTOExtended> tornei = new HashSet<>();

        SquadraDTOExtended squadraDTOExtended = new SquadraDTOExtended();
        squadraDTOExtended.setGiocatori(new HashSet<>());
        TorneoDTOExtended torneoDTOExtended = new TorneoDTOExtended();
        torneoDTOExtended.setSquadre(new HashSet<>());

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DataSourceUtils.getConnection(Objects.requireNonNull(((DataSourceTransactionManager) transactionManager).getDataSource()));
            ps = con.prepareStatement(
                    "select * from torneo t " +
                            "join squadra_torneo st on t.id = st.id_torneo " +
                            "join squadra s on st.id_squadra = s.id " +
                            "join giocatore g on s.id = g.id_squadra " +
                            "join tifoseria ti on s.id = ti.id_squadra " +
                            "order by t.id, s.id");
            rs = ps.executeQuery();
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
        return tornei;
    }

}
