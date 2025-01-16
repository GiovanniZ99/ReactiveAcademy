package it.reactive.academy.springMvc.repository.queryforx;

import it.reactive.academy.springMvc.dto.extended.TorneoDTOExtended;
import it.reactive.academy.springMvc.entity.TorneoEntity;
import it.reactive.academy.springMvc.repository.dao.TorneoDao;
import it.reactive.academy.springMvc.utility.Costanti;
import it.reactive.academy.springMvc.utility.mapper.TorneoMapper;
import it.reactive.academy.springMvc.utility.rowmapper.TorneoRowMapper;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

@Repository
@Profile(Costanti.TORNEO_DAO_SPRING_JDBC_QUERY_FOR_X)
public class TorneoDaoImpl implements TorneoDao {

    private final JdbcTemplate jdbcTemplate;

    public TorneoDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public TorneoDTOExtended create(String nomeTorneo) throws SQLException {
        TorneoEntity torneoEntity = new TorneoEntity();
        torneoEntity.setNomeTorneo(nomeTorneo);

        String s = "insert into torneo (nome_torneo) values (?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(s, nomeTorneo, keyHolder);
        torneoEntity.setIdTorneo((Integer) keyHolder.getKey());

        return TorneoMapper.torneoEntityToDtoExtended(torneoEntity);
    }

    @Override
    public TorneoDTOExtended findById(Integer idTorneo) throws SQLException {
        TorneoEntity torneoEntity = jdbcTemplate.queryForObject(
                "select id, nome_torneo from torneo where id = ?",
                new TorneoRowMapper(), idTorneo);

        return TorneoMapper.torneoEntityToDtoExtended(torneoEntity);
    }

    @Override
    public void delete(Integer id) throws SQLException {
        String deleteSquadraTorneoSql = "delete from squadra_torneo where id_torneo = ?";
        String deleteTorneoSql = "delete from torneo where id = ?";

        jdbcTemplate.update(deleteSquadraTorneoSql, id);
        jdbcTemplate.update(deleteTorneoSql, id);
    }
}

