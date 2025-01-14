package it.reactive.academy.springMvc.repository.query;

import it.reactive.academy.springMvc.dto.extended.SquadraDTOExtended;
import it.reactive.academy.springMvc.dto.extended.SquadraTorneoDTOExtended;
import it.reactive.academy.springMvc.dto.extended.TorneoDTOExtended;
import it.reactive.academy.springMvc.model.*;
import it.reactive.academy.springMvc.repository.dao.SquadraTorneoDao;
import it.reactive.academy.springMvc.utility.Costanti;
import it.reactive.academy.springMvc.utility.mapper.GiocatoreMapper;
import it.reactive.academy.springMvc.utility.mapper.SquadraTorneoMapper;
import it.reactive.academy.springMvc.utility.mapper.TifoseriaMapper;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
@Profile(Costanti.TORNEO_DAO_SPRING_JDBC_QUERY)
public class SquadraTorneoDaoImpl implements SquadraTorneoDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public SquadraTorneoDaoImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public SquadraTorneoDTOExtended create(Integer idTorneo, Integer idSquadra) throws SQLException {
        SquadraTorneoModel squadraTorneoModel = new SquadraTorneoModel();

        String s = "insert into squadra_torneo (id_squadra, id_torneo) values (:idSquadra, :idTorneo)";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("idSquadra", idSquadra);
        params.addValue("idTorneo", idTorneo);
        namedParameterJdbcTemplate.update(s, params);

        squadraTorneoModel.setIdTorneo(idTorneo);
        squadraTorneoModel.setIdSquadra(idSquadra);
        return SquadraTorneoMapper.squadraModelToDtoExtended(squadraTorneoModel);
    }

    @Override
    public Set<Integer> readAllTeamsById(Integer idTorneo) throws SQLException {
        Set<Integer> setIdSquadre = new HashSet<>();
        String s = "select id_squadra from squadra_torneo where id_torneo = :idTorneo";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("idTorneo", idTorneo);

        namedParameterJdbcTemplate.query(s, params, new RowMapper<TorneoModel>() {
            @Override
            public TorneoModel mapRow(ResultSet rs, int rowNum) throws SQLException {
                setIdSquadre.add(rs.getInt("id_squadra"));
                return null;
            }
        });
        return setIdSquadre;
    }

    public LinkedHashMap<Integer, Set<Integer>> readAllTornei() {
        String sql = "SELECT id_squadra, id_torneo FROM squadra_torneo ORDER BY id_torneo";

        LinkedHashMap<Integer, Set<Integer>> mappaId = new LinkedHashMap<>();

        namedParameterJdbcTemplate.query(sql, new RowMapper<Void>() {
            @Override
            public Void mapRow(ResultSet rs, int rowNum) throws SQLException {
                Integer idTorneo = rs.getInt("id_torneo");
                Integer idSquadra = rs.getInt("id_squadra");

                mappaId.computeIfAbsent(idTorneo, k -> new HashSet<>()).add(idSquadra);

                return null;
            }
        });

        return mappaId;
    }

    public Set<Integer> readAllTorneoByIdSquadra(Integer idSquadra) {
        String sql = "SELECT id_squadra, id_torneo FROM squadra_torneo WHERE id_squadra = :idSquadra";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("idSquadra", idSquadra);

        Set<Integer> listaIdTornei = new HashSet<>();

        namedParameterJdbcTemplate.query(sql, params, new RowMapper<Void>() {
            @Override
            public Void mapRow(ResultSet rs, int rowNum) throws SQLException {
                listaIdTornei.add(rs.getInt("id_torneo"));
                return null;
            }
        });

        return listaIdTornei;
    }

    @Deprecated
    @Override
    public Set<TorneoDTOExtended> readAllTorneo() throws SQLException {
        String s = "select * from torneo t " +
                "join squadra_torneo st on t.id = st.id_torneo " +
                "join squadra s on st.id_squadra = s.id " +
                "join giocatore g on s.id = g.id_squadra " +
                "join tifoseria ti on s.id = ti.id_squadra " +
                "order by t.id, s.id";

        Set<TorneoDTOExtended> tornei = new HashSet<>();

        namedParameterJdbcTemplate.query(s,
                new RowMapper<TorneoDTOExtended>() {
                    private TorneoDTOExtended torneoDTOExtended;
                    private SquadraDTOExtended squadraDTOExtended;
                    @Override
                    public TorneoDTOExtended mapRow(ResultSet rs, int rowNum) throws SQLException {
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

                        if (squadraDTOExtended == null || !squadraDTOExtended.getIdSquadra().equals(squadraModel.getIdSquadra())) {
                            squadraDTOExtended = new SquadraDTOExtended();
                            squadraDTOExtended.setGiocatori(new HashSet<>());
                        }
                        squadraDTOExtended.setIdSquadra(squadraModel.getIdSquadra());
                        squadraDTOExtended.setNome(squadraModel.getNome());
                        squadraDTOExtended.setColoriSociali(squadraModel.getColoriSociali());
                        squadraDTOExtended.getGiocatori().add(GiocatoreMapper.giocatoreModelToDTOExtended(giocatoreModel));
                        squadraDTOExtended.setTifoseria(TifoseriaMapper.tifoseriaModelToDtoExtended(tifoseriaModel));

                        if (torneoDTOExtended == null || !torneoDTOExtended.getIdTorneo().equals(torneoModel.getIdTorneo())) {
                            torneoDTOExtended = new TorneoDTOExtended();
                            torneoDTOExtended.setSquadre(new HashSet<>());
                        }

                        torneoDTOExtended.setIdTorneo(torneoModel.getIdTorneo());
                        torneoDTOExtended.setNomeTorneo(torneoModel.getNomeTorneo());
                        torneoDTOExtended.getSquadre().add(squadraDTOExtended);

                        tornei.add(torneoDTOExtended);

                        return torneoDTOExtended;
                    }
                });

        return tornei;
    }
}
