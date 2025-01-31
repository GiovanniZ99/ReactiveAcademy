package it.reactive.academy.springMvc.repository.querypsc;

import it.reactive.academy.springMvc.dto.extended.SquadraDTOExtended;
import it.reactive.academy.springMvc.dto.extended.SquadraTorneoDTOExtended;
import it.reactive.academy.springMvc.dto.extended.TorneoDTOExtended;
import it.reactive.academy.springMvc.utility.mapper.*;
import it.reactive.academy.springMvc.entity.*;
import it.reactive.academy.springMvc.repository.dao.SquadraTorneoDao;
import it.reactive.academy.springMvc.utility.Costanti;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

@Repository
@Profile(Costanti.TORNEO_DAO_SPRING_JDBC_QUERY_PSC)
public class SquadraTorneoDaoImpl implements SquadraTorneoDao {

    private final JdbcTemplate jdbcTemplate;

    public SquadraTorneoDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
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

        String s = "insert into squadra_torneo (id_squadra, id_torneo) values (?, ?)";
        jdbcTemplate.update(s, squadra.getIdSquadra(), torneo.getIdTorneo());
        return SquadraTorneoMapper.squadraTorneoEntityToDtoExtended(squadraTorneoEntity);
    }

    @Override
    public Set<SquadraDTOExtended> readAllTeamsById(TorneoDTOExtended torneoDTOExtended) throws SQLException {
        List<SquadraEntity> listaSquadre = new ArrayList<>();

        String s = "select id_squadra from squadra_torneo where id_torneo = ?";
        ResultSetExtractor<List<SquadraEntity>> rse = new ResultSetExtractor<List<SquadraEntity>>() {
            @Override
            public List<SquadraEntity> extractData(ResultSet rs) throws SQLException, DataAccessException {
                while (rs.next()) {
                    SquadraEntity squadra = new SquadraEntity();
                    squadra.setIdSquadra(rs.getInt(1));
                    listaSquadre.add(squadra);
                }
                return listaSquadre;
            }
        };
        jdbcTemplate.query(s, rse, torneoDTOExtended.getIdTorneo());
        return listaSquadre.stream().map(SquadraMapper::squadraEntityToDtoExtendended).collect(Collectors.toSet());
    }

    @Override
    public Set<TorneoDTOExtended> readAllTorneoByIdSquadra(SquadraDTOExtended squadraDTOExtended) throws SQLException {
        String s = "select id_squadra, id_torneo from squadra_torneo where id_squadra = ?";

        List<TorneoEntity> listaTornei = jdbcTemplate.query(s, new Object[]{squadraDTOExtended.getIdSquadra()}, new ResultSetExtractor<List<TorneoEntity>>() {
            public List<TorneoEntity> extractData(ResultSet rs) throws SQLException {
                List<TorneoEntity> tornei = new ArrayList<>();
                while (rs.next()) {
                    TorneoEntity torneo = new TorneoEntity();
                    torneo.setIdTorneo(rs.getInt("id_torneo"));
                    tornei.add(torneo);
                }
                return tornei;
            }
        });
        return listaTornei.stream()
                .map(TorneoMapper::torneoEntityToDtoExtended)
                .collect(Collectors.toSet());
    }


    @Deprecated
    @Override
    public Set<TorneoDTOExtended> readAllTorneo() throws SQLException {
        Set<TorneoDTOExtended> tornei = new HashSet<>();
        final SquadraDTOExtended[] squadraDTOExtended = {new SquadraDTOExtended()};
        squadraDTOExtended[0].setGiocatori(new HashSet<>());
        final TorneoDTOExtended[] torneoDTOExtended = {new TorneoDTOExtended()};
        torneoDTOExtended[0].setSquadre(new HashSet<>());
        String s = "select * from torneo t " +
                "join squadra_torneo st on t.id = st.id_torneo " +
                "join squadra s on st.id_squadra = s.id " +
                "join giocatore g on s.id = g.id_squadra " +
                "join tifoseria ti on s.id = ti.id_squadra " +
                "order by t.id, s.id";
        ResultSetExtractor<Set<TorneoDTOExtended>> rse = new ResultSetExtractor<Set<TorneoDTOExtended>>() {
            @Override
            public Set<TorneoDTOExtended> extractData(ResultSet rs) throws SQLException, DataAccessException {
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

                    if (!Objects.equals(squadraDTOExtended[0].getIdSquadra(), squadraEntity.getIdSquadra())) {
                        squadraDTOExtended[0] = new SquadraDTOExtended();
                        squadraDTOExtended[0].setGiocatori(new HashSet<>());
                    }
                    squadraDTOExtended[0].setIdSquadra(squadraEntity.getIdSquadra());
                    squadraDTOExtended[0].setNome(squadraEntity.getNome());
                    squadraDTOExtended[0].setColoriSociali(squadraEntity.getColoriSociali());
                    squadraDTOExtended[0].getGiocatori().add(GiocatoreMapper.giocatoreEntityToDTOExtended(giocatoreEntity));
                    squadraDTOExtended[0].setTifoseria(TifoseriaMapper.tifoseriaEntityToDtoExtended(tifoseriaEntity));

                    if (!Objects.equals(torneoDTOExtended[0].getIdTorneo(), torneoEntity.getIdTorneo())) {
                        torneoDTOExtended[0] = new TorneoDTOExtended();
                        torneoDTOExtended[0].setSquadre(new HashSet<>());
                    }
                    torneoDTOExtended[0].setIdTorneo(torneoEntity.getIdTorneo());
                    torneoDTOExtended[0].getSquadre().add(squadraDTOExtended[0]);
                    torneoDTOExtended[0].setNomeTorneo(torneoEntity.getNomeTorneo());
                    tornei.add(torneoDTOExtended[0]);
                }
                return tornei;
            }

        };
        jdbcTemplate.query(s, rse);
        return tornei;
    }
}
