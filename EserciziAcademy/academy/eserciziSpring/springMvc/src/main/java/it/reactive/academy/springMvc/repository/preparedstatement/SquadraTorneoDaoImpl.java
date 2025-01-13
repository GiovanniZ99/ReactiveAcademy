package it.reactive.academy.springMvc.repository.preparedstatement;

import it.reactive.academy.springMvc.utility.Costanti;
import it.reactive.academy.springMvc.dto.extended.SquadraDTOExtended;
import it.reactive.academy.springMvc.dto.extended.SquadraTorneoDTOExtended;
import it.reactive.academy.springMvc.dto.extended.TorneoDTOExtended;
import it.reactive.academy.springMvc.utility.mapper.GiocatoreMapper;
import it.reactive.academy.springMvc.utility.mapper.SquadraTorneoMapper;
import it.reactive.academy.springMvc.utility.mapper.TifoseriaMapper;
import it.reactive.academy.springMvc.model.*;
import it.reactive.academy.springMvc.repository.dao.SquadraTorneoDao;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;

import java.sql.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Repository
@Profile(Costanti.TORNEO_DAO_JDBC_PREPAREDSTATEMENT)
public class SquadraTorneoDaoImpl implements SquadraTorneoDao {

    private final PlatformTransactionManager transactionManager;

    public SquadraTorneoDaoImpl(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    @Override
    public SquadraTorneoDTOExtended create(Integer idTorneo, Integer idSquadra) throws SQLException {
        SquadraTorneoModel squadraTorneoModel = new SquadraTorneoModel();
        squadraTorneoModel.setIdTorneo(idTorneo);
        squadraTorneoModel.setIdSquadra(idSquadra);

        Connection con = DataSourceUtils.getConnection(Objects.requireNonNull(((DataSourceTransactionManager) transactionManager).getDataSource()));
        try (PreparedStatement ps = con.prepareStatement(
                "insert into squadra_torneo (id_squadra, id_torneo) values (?, ?)")) {

            ps.setInt(1, idSquadra);
            ps.setInt(2, idTorneo);
            ps.executeUpdate();

        }
        DataSourceUtils.releaseConnection(con, ((DataSourceTransactionManager) transactionManager).getDataSource());
        return SquadraTorneoMapper.squadraModelToDtoExtended(squadraTorneoModel);
    }

    @Override
    public Set<Integer> readAllTeams(Integer idTorneo) throws SQLException {
        Set<Integer> setIdSquadre = new HashSet<>();

        Connection con = DataSourceUtils.getConnection(Objects.requireNonNull(((DataSourceTransactionManager) transactionManager).getDataSource()));
        try (PreparedStatement ps = con.prepareStatement(
                "select id_squadra from squadra_torneo where id_torneo = ?")) {

            ps.setInt(1, idTorneo);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    setIdSquadre.add(rs.getInt(1));
                }
            }
        }
        DataSourceUtils.releaseConnection(con, ((DataSourceTransactionManager) transactionManager).getDataSource());
        return setIdSquadre;
    }

    @Override
    public Set<TorneoDTOExtended> readAllTorneo() throws SQLException {
        Set<TorneoDTOExtended> tornei = new HashSet<>();

        SquadraDTOExtended squadraDTOExtended = new SquadraDTOExtended();
        squadraDTOExtended.setGiocatori(new HashSet<>());
        Set<Integer> idSquadre = new HashSet<>();
        TorneoDTOExtended torneoDTOExtended = new TorneoDTOExtended();
        torneoDTOExtended.setSquadre(new HashSet<>());

        Connection con = DataSourceUtils.getConnection(Objects.requireNonNull(((DataSourceTransactionManager) transactionManager).getDataSource()));
        try (PreparedStatement ps = con.prepareStatement(
                "select * from torneo t " +
                        "join squadra_torneo st on t.id = st.id_torneo " +
                        "join squadra s on st.id_squadra = s.id " +
                        "join giocatore g on s.id = g.id_squadra " +
                        "join tifoseria ti on s.id = ti.id_squadra "+
                        "order by t.id, s.id")) {

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    TorneoModel torneoModel = new TorneoModel();
                    SquadraModel squadraModel = new SquadraModel();
                    GiocatoreModel giocatoreModel = new GiocatoreModel();
                    TifoseriaModel tifoseriaModel = new TifoseriaModel();

                    torneoModel.setIdTorneo(rs.getInt(1));
                    torneoModel.setNomeTorneo(rs.getString(2));

                    squadraModel.setIdSquadra(rs.getInt(3));
                    squadraModel.setNome(rs.getString(6));
                    squadraModel.setColoriSociali(rs.getString(7));

                    tifoseriaModel.setIdTifoseria(rs.getInt(12));
                    tifoseriaModel.setNomeTifoseria(rs.getString(13));

                    giocatoreModel.setIdGiocatore(rs.getInt(8));
                    giocatoreModel.setNomeCognome(rs.getString(9));
                    giocatoreModel.setNumeroAmmonizioni(rs.getInt(10));

                    if(!idSquadre.contains(squadraModel.getIdSquadra())){
                        squadraDTOExtended = new SquadraDTOExtended();
                        squadraDTOExtended.setGiocatori(new HashSet<>());
                    }
                    idSquadre.add(squadraModel.getIdSquadra());
                    squadraDTOExtended.setIdSquadra(squadraModel.getIdSquadra());
                    squadraDTOExtended.setNome(squadraModel.getNome());
                    squadraDTOExtended.setColoriSociali(squadraModel.getColoriSociali());
                    squadraDTOExtended.getGiocatori().add(GiocatoreMapper.giocatoreModelToDTOExtended(giocatoreModel));
                    squadraDTOExtended.setTifoseria(TifoseriaMapper.tifoseriaModelToDtoExtended(tifoseriaModel));

                    if (!Objects.equals(torneoDTOExtended.getIdTorneo(), torneoModel.getIdTorneo())) {
                        torneoDTOExtended = new TorneoDTOExtended();
                        torneoDTOExtended.setSquadre(new HashSet<>());
                    }
                    torneoDTOExtended.setIdTorneo(torneoModel.getIdTorneo());
                    torneoDTOExtended.getSquadre().add(squadraDTOExtended);
                    torneoDTOExtended.setNomeTorneo(torneoModel.getNomeTorneo());
                    tornei.add(torneoDTOExtended);
                }
            }
        }
        DataSourceUtils.releaseConnection(con, ((DataSourceTransactionManager) transactionManager).getDataSource());
        return tornei;
    }
}
