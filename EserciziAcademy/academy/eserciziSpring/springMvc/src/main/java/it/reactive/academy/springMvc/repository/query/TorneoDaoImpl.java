package it.reactive.academy.springMvc.repository.query;

import it.reactive.academy.springMvc.dto.extended.TorneoDTOExtended;
import it.reactive.academy.springMvc.utility.mapper.TorneoMapper;
import it.reactive.academy.springMvc.model.TorneoModel;
import it.reactive.academy.springMvc.repository.dao.TorneoDao;
import it.reactive.academy.springMvc.utility.Costanti;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
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
        TorneoModel torneoModel = new TorneoModel();
        torneoModel.setNomeTorneo(nomeTorneo);

        String s = "insert into torneo (nome_torneo) values (:nomeTorneo)";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("nomeTorneo", nomeTorneo);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(s, params, keyHolder);
        torneoModel.setIdTorneo((Integer) Objects.requireNonNull(keyHolder.getKeys().get("id")));

        return TorneoMapper.torneoModelToDtoExtended(torneoModel);
    }

    @Override
    public TorneoDTOExtended findById(Integer idTorneo) throws SQLException {
        TorneoModel torneoModel = new TorneoModel();

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("idTorneo", idTorneo);
        namedParameterJdbcTemplate.query("select id, nome_torneo from torneo where id = :idTorneo",
                params, (rs, rowNum) -> {
                    torneoModel.setIdTorneo(rs.getInt(1));
                    torneoModel.setNomeTorneo(rs.getString(2));
                    return torneoModel;
                });
        return TorneoMapper.torneoModelToDtoExtended(torneoModel);
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

