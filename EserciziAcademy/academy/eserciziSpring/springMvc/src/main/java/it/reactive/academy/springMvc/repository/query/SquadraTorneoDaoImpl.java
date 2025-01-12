package it.reactive.academy.springMvc.repository.query;

import it.reactive.academy.springMvc.dto.extended.SquadraTorneoDTOExtended;
import it.reactive.academy.springMvc.dto.extended.TorneoDTOExtended;
import it.reactive.academy.springMvc.utility.mapper.SquadraTorneoMapper;
import it.reactive.academy.springMvc.utility.mapper.TorneoMapper;
import it.reactive.academy.springMvc.model.*;
import it.reactive.academy.springMvc.repository.dao.SquadraTorneoDao;
import it.reactive.academy.springMvc.utility.Costanti;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    public Set<Integer> readAllTeams(Integer idTorneo) throws SQLException {
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

    @Override
    public Set<TorneoDTOExtended> readAllTorneo() throws SQLException {
        String s = "select * from torneo t " +
                "join squadra_torneo st on t.id = st.id_torneo " +
                "join squadra s on st.id_squadra = s.id " +
                "join giocatore g on s.id = g.id_squadra " +
                "join tifoseria ti on s.id = ti.id_squadra";
        MapSqlParameterSource emptyParams = new MapSqlParameterSource();
       List<TorneoModel> sqlListaResult = namedParameterJdbcTemplate.query(s, emptyParams, new BeanPropertyRowMapper<>(TorneoModel.class));

        return sqlListaResult.stream().map(TorneoMapper::torneoModelToDtoExtended).collect(Collectors.toSet());
    }
}
