package it.reactive.academy.springMvc.repository.querypsc;

import it.reactive.academy.springMvc.dto.extended.SquadraDTOExtended;
import it.reactive.academy.springMvc.dto.extended.SquadraTorneoDTOExtended;
import it.reactive.academy.springMvc.dto.extended.TorneoDTOExtended;
import it.reactive.academy.springMvc.utility.mapper.GiocatoreMapper;
import it.reactive.academy.springMvc.utility.mapper.SquadraTorneoMapper;
import it.reactive.academy.springMvc.utility.mapper.TifoseriaMapper;
import it.reactive.academy.springMvc.model.*;
import it.reactive.academy.springMvc.repository.dao.SquadraTorneoDao;
import it.reactive.academy.springMvc.utility.Costanti;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
@Profile(Costanti.TORNEO_DAO_SPRING_JDBC_QUERY_PSC)
public class SquadraTorneoDaoImpl implements SquadraTorneoDao {

    private final JdbcTemplate jdbcTemplate;

    public SquadraTorneoDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public SquadraTorneoDTOExtended create(Integer idTorneo, Integer idSquadra) throws SQLException {
        SquadraTorneoModel squadraTorneoModel = new SquadraTorneoModel();
        squadraTorneoModel.setIdSquadra(idSquadra);
        squadraTorneoModel.setIdTorneo(idTorneo);
        String s = "insert into squadra_torneo (id_squadra, id_torneo) values (?, ?)";
        jdbcTemplate.update(s, idSquadra, idTorneo);
        return SquadraTorneoMapper.squadraModelToDtoExtended(squadraTorneoModel);
    }

    @Override
    public Set<Integer> readAllTeamsById(Integer idTorneo) throws SQLException {
        Set<Integer> setIdSquadre = new HashSet<>();

        String s = "select id_squadra from squadra_torneo where id_torneo = ?";
        ResultSetExtractor<Set<Integer>> rse = new ResultSetExtractor<Set<Integer>>() {
            @Override
            public Set<Integer> extractData(ResultSet rs) throws SQLException, DataAccessException {
                while (rs.next()) {
                    setIdSquadre.add(rs.getInt(1));
                }
                return setIdSquadre;
            }
        };
        jdbcTemplate.query(s, rse, idTorneo);
        return setIdSquadre;
    }

    @Override
    public LinkedHashMap<Integer, Set<Integer>> readAllTornei() throws SQLException {
        String sql = "select id_squadra, id_torneo from squadra_torneo order by id_torneo";

        PreparedStatementCreator psc = connection -> connection.prepareStatement(sql);
        ResultSetExtractor<LinkedHashMap<Integer, Set<Integer>>> rse = rs -> {
            LinkedHashMap<Integer, Set<Integer>> mappaId = new LinkedHashMap<>();
            while (rs.next()) {
                Integer idTorneo = rs.getInt("id_torneo");
                Integer idSquadra = rs.getInt("id_squadra");
                mappaId.computeIfAbsent(idTorneo, k -> new HashSet<>()).add(idSquadra);
            }
            return mappaId;
        };

        return jdbcTemplate.query(psc, rse);
    }

    @Override
    public Set<Integer> readAllTorneoByIdSquadra(Integer idSquadra) throws SQLException {
        String sql = "select id_squadra, id_torneo from squadra_torneo where id_squadra = ?";
        Object[] params = new Object[]{idSquadra};
        return jdbcTemplate.query(sql, params, new ResultSetExtractor<Set<Integer>>() {
            public Set<Integer> extractData(ResultSet rs) throws SQLException {
                Set<Integer> listaIdTornei = new HashSet<>();
                while (rs.next()) {
                    listaIdTornei.add(rs.getInt(1));
                }
                return listaIdTornei;
            }
        });
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
                "join tifoseria ti on s.id = ti.id_squadra "+
                "order by t.id, s.id";
        ResultSetExtractor<Set<TorneoDTOExtended>> rse = new ResultSetExtractor<Set<TorneoDTOExtended>>() {
            @Override
            public Set<TorneoDTOExtended> extractData(ResultSet rs) throws SQLException, DataAccessException {
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

                    if(!Objects.equals(squadraDTOExtended[0].getIdSquadra(), squadraModel.getIdSquadra())){
                        squadraDTOExtended[0] = new SquadraDTOExtended();
                        squadraDTOExtended[0].setGiocatori(new HashSet<>());
                    }
                    squadraDTOExtended[0].setIdSquadra(squadraModel.getIdSquadra());
                    squadraDTOExtended[0].setNome(squadraModel.getNome());
                    squadraDTOExtended[0].setColoriSociali(squadraModel.getColoriSociali());
                    squadraDTOExtended[0].getGiocatori().add(GiocatoreMapper.giocatoreModelToDTOExtended(giocatoreModel));
                    squadraDTOExtended[0].setTifoseria(TifoseriaMapper.tifoseriaModelToDtoExtended(tifoseriaModel));

                    if (!Objects.equals(torneoDTOExtended[0].getIdTorneo(), torneoModel.getIdTorneo())) {
                        torneoDTOExtended[0] = new TorneoDTOExtended();
                        torneoDTOExtended[0].setSquadre(new HashSet<>());
                    }
                    torneoDTOExtended[0].setIdTorneo(torneoModel.getIdTorneo());
                    torneoDTOExtended[0].getSquadre().add(squadraDTOExtended[0]);
                    torneoDTOExtended[0].setNomeTorneo(torneoModel.getNomeTorneo());
                    tornei.add(torneoDTOExtended[0]);
                }
                return tornei;
            }

        };
        jdbcTemplate.query(s, rse);
        return tornei;
    }
}
