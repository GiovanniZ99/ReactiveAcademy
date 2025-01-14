package it.reactive.academy.springMvc.repository.queryforx;

import it.reactive.academy.springMvc.dto.extended.SquadraTorneoDTOExtended;
import it.reactive.academy.springMvc.dto.extended.TorneoDTOExtended;
import it.reactive.academy.springMvc.model.*;
import it.reactive.academy.springMvc.repository.dao.SquadraTorneoDao;
import it.reactive.academy.springMvc.utility.Costanti;
import it.reactive.academy.springMvc.utility.mapper.SquadraTorneoMapper;
import it.reactive.academy.springMvc.utility.mapper.TorneoMapper;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

@Repository
@Profile(Costanti.TORNEO_DAO_SPRING_JDBC_QUERY_FOR_X)
public class SquadraTorneoDaoImpl implements SquadraTorneoDao {

    private final JdbcTemplate jdbcTemplate;

    public SquadraTorneoDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public SquadraTorneoDTOExtended create(Integer idTorneo, Integer idSquadra) throws SQLException {
        SquadraTorneoModel squadraTorneoModel = new SquadraTorneoModel();

        String s = "insert into squadra_torneo (id_squadra, id_torneo) values (?,?)";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("idTorneo", idTorneo);
        params.addValue("idSquadra", idSquadra);
        jdbcTemplate.update(s, new Object[]{idSquadra, idTorneo});

        squadraTorneoModel.setIdTorneo(idTorneo);
        squadraTorneoModel.setIdSquadra(idSquadra);
        return SquadraTorneoMapper.squadraModelToDtoExtended(squadraTorneoModel);
    }

    @Override
    public Set<Integer> readAllTeamsById(Integer idTorneo) throws SQLException {
        String s = "select id_squadra from squadra_torneo where id_torneo = ?";

        List<Integer> mapSquadre = jdbcTemplate.queryForList(s, Integer.class, idTorneo);
        return new HashSet<>(mapSquadre);
    }

    @Override
    public LinkedHashMap<Integer, Set<Integer>> readAllTornei() throws SQLException {
        String s = "select id_squadra, id_torneo from squadra_torneo order by id_torneo";

        LinkedHashMap<Integer, Set<Integer>> mappaId = new LinkedHashMap<>();

        jdbcTemplate.query(s, new RowMapper<Void>() {
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

    @Override
    public Set<Integer> readAllTorneoByIdSquadra(Integer idSquadra) throws SQLException {
        String s = "select id_squadra, id_torneo from squadra_torneo where id_squadra = ?";

        List<Integer> listaIdTornei = jdbcTemplate.queryForList(s, Integer.class, idSquadra);

        return new HashSet<>(listaIdTornei);
    }

    @Deprecated
    @Override
    public Set<TorneoDTOExtended> readAllTorneo() throws SQLException {
        String s = "select * from torneo t " +
                "join squadra_torneo st on t.id = st.id_torneo " +
                "join squadra s on st.id_squadra = s.id " +
                "join giocatore g on s.id = g.id_squadra " +
                "join tifoseria ti on s.id = ti.id_squadra "+
                "order by t.id, s.id";

        List<TorneoModel> sqlListaResult = jdbcTemplate.query(s, new BeanPropertyRowMapper<>(TorneoModel.class));

        return sqlListaResult.stream().map(TorneoMapper::torneoModelToDtoExtended).collect(Collectors.toSet());
    }
}