package it.reactive.academy.springMvc.repository.query;

import it.reactive.academy.springMvc.dto.extended.TorneoDTOExtended;
import it.reactive.academy.springMvc.utility.mapper.TorneoMapper;
import it.reactive.academy.springMvc.entity.TorneoEntity;
import it.reactive.academy.springMvc.repository.dao.TorneoDao;
import it.reactive.academy.springMvc.utility.Costanti;
import it.reactive.academy.springMvc.utility.rowmapper.TorneoRowMapper;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@Repository
@Profile(Costanti.TORNEO_DAO_SPRING_JDBC_QUERY)
public class TorneoDaoImpl implements TorneoDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public TorneoDaoImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public TorneoDTOExtended create(String nomeTorneo) throws SQLException {
        TorneoEntity torneoEntity = new TorneoEntity();
        torneoEntity.setNomeTorneo(nomeTorneo);

        String s = "insert into torneo (nome_torneo) values (:nomeTorneo)";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("nomeTorneo", nomeTorneo);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(s, params, keyHolder);
        torneoEntity.setIdTorneo((Integer) Objects.requireNonNull(keyHolder.getKeys().get("id")));

        return TorneoMapper.torneoEntityToDtoExtended(torneoEntity);
    }

    @Override
    public TorneoDTOExtended findById(Integer idTorneo) throws SQLException {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("idTorneo", idTorneo);
        List<TorneoEntity> torneo = namedParameterJdbcTemplate.query(
                "select id, nome_torneo from torneo where id = :idTorneo",
                params,
               new TorneoRowMapper());

        if(torneo.get(0) == null){
            torneo.add(new TorneoEntity());
        }
        return TorneoMapper.torneoEntityToDtoExtended(torneo.get(0));
    }

    @Override
    public void delete(Integer id) throws SQLException {
        String deleteSquadraTorneoSql = "delete from squadra_torneo where id_torneo = :id";
        String deleteTorneoSql = "delete from torneo where id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        namedParameterJdbcTemplate.update(deleteSquadraTorneoSql, params);
        namedParameterJdbcTemplate.update(deleteTorneoSql,params);
    }
}

