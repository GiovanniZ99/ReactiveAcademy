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
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

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
        String s = "insert into squadra_torneo (id_squadra, id_torneo) values (?, ?)";
        jdbcTemplate.update(s, idSquadra, idTorneo);
        squadraTorneoModel.setIdSquadra(idSquadra);
        squadraTorneoModel.setIdTorneo(idTorneo);
        return SquadraTorneoMapper.squadraModelToDtoExtended(squadraTorneoModel);
    }

    @Override
    public Set<Integer> readAllTeams(Integer idTorneo) throws SQLException {
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
    public Set<TorneoDTOExtended> readAllTorneo() throws SQLException {
        Set<TorneoDTOExtended> tornei = new HashSet<>();

        String s = "select * from torneo t " +
                "join squadra_torneo st on t.id = st.id_torneo " +
                "join squadra s on st.id_squadra = s.id " +
                "join giocatore g on s.id = g.id_squadra " +
                "join tifoseria ti on s.id = ti.id_squadra";
        ResultSetExtractor <Set<TorneoDTOExtended>> rse = new ResultSetExtractor<Set<TorneoDTOExtended>>() {
            @Override
            public Set<TorneoDTOExtended> extractData(ResultSet rs) throws SQLException, DataAccessException {
                TorneoDTOExtended torneoDTOExtended = null;
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

                    SquadraDTOExtended squadraDTOExtended = new SquadraDTOExtended();
                    squadraDTOExtended.setIdSquadra(squadraModel.getIdSquadra());
                    squadraDTOExtended.setNome(squadraModel.getNome());
                    squadraDTOExtended.setColoriSociali(squadraModel.getColoriSociali());
                    squadraDTOExtended.getGiocatori().add(GiocatoreMapper.giocatoreModelToDTOExtended(giocatoreModel));
                    squadraDTOExtended.setTifoseria(TifoseriaMapper.tifoseriaModelToDtoExtended(tifoseriaModel));
                    if (torneoDTOExtended == null) {
                        torneoDTOExtended = new TorneoDTOExtended();
                        torneoDTOExtended.setSquadre(new HashSet<>());
                    }
                    torneoDTOExtended.setIdTorneo(torneoModel.getIdTorneo());
                    torneoDTOExtended.setNomeTorneo(torneoModel.getNomeTorneo());

                    torneoDTOExtended.getSquadre().add(squadraDTOExtended);
                    tornei.add(torneoDTOExtended);
                }
                return tornei;
            }
        };
        jdbcTemplate.query(s, rse);
        return tornei;
    }
}
