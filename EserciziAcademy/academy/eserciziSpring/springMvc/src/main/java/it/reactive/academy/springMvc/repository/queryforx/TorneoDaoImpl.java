package it.reactive.academy.springMvc.repository.queryforx;

import it.reactive.academy.springMvc.dto.extended.TorneoDTOExtended;
import it.reactive.academy.springMvc.entity.SquadraEntity;
import it.reactive.academy.springMvc.entity.TorneoEntity;
import it.reactive.academy.springMvc.repository.dao.TorneoDao;
import it.reactive.academy.springMvc.utility.Costanti;
import it.reactive.academy.springMvc.utility.mapper.SquadraMapper;
import it.reactive.academy.springMvc.utility.mapper.TorneoMapper;
import it.reactive.academy.springMvc.utility.rowmapper.TorneoRowMapper;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.stream.Collectors;

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
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(s, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, nomeTorneo);
            return ps;
        }, keyHolder);
        torneoEntity.setIdTorneo((Integer) keyHolder.getKeyList().get(0).get("id"));

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
    public Set<TorneoDTOExtended> findAll() throws SQLException {
        List<Map<String, Object>> mapSquadra = jdbcTemplate.queryForList("select * from torneo");

        List<TorneoEntity> tornei = new ArrayList<>();
        for (Map<String, Object> map : mapSquadra) {
            TorneoEntity torneo = new TorneoEntity();
            torneo.setIdTorneo((Integer) map.get("id"));
            torneo.setNomeTorneo((String) map.get("nome_torneo"));
            tornei.add(torneo);
        }
        return tornei.stream().map(TorneoMapper::torneoEntityToDtoExtended).collect(Collectors.toSet());
    }

    @Override
    public void delete(Integer id) throws SQLException {
        String deleteSquadraTorneoSql = "delete from squadra_torneo where id_torneo = ?";
        String deleteTorneoSql = "delete from torneo where id = ?";

        jdbcTemplate.update(deleteSquadraTorneoSql, id);
        jdbcTemplate.update(deleteTorneoSql, id);
    }
}

