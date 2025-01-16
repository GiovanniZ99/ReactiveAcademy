package it.reactive.academy.springMvc.repository.queryforx;

import it.reactive.academy.springMvc.dto.extended.SquadraDTOExtended;
import it.reactive.academy.springMvc.utility.mapper.SquadraMapper;
import it.reactive.academy.springMvc.entity.SquadraEntity;
import it.reactive.academy.springMvc.repository.dao.SquadraDao;
import it.reactive.academy.springMvc.utility.Costanti;
import it.reactive.academy.springMvc.utility.rowmapper.SquadraRowMapper;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

@Repository
@Profile(Costanti.TORNEO_DAO_SPRING_JDBC_QUERY_FOR_X)
public class SquadraDaoImpl implements SquadraDao {

    private final JdbcTemplate jdbcTemplate;

    public SquadraDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public SquadraDTOExtended create(SquadraDTOExtended squadraDTOExtended) throws SQLException {
        SquadraEntity squadraEntity = SquadraMapper.squadraDtoExtendedToEntity(squadraDTOExtended);

        String s = "insert into squadra (nome, colori_sociali) values (?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(s,
                new Object[]{squadraDTOExtended.getNome(), squadraDTOExtended.getColoriSociali()},
                keyHolder);
        squadraEntity.setIdSquadra((Objects.requireNonNull(keyHolder.getKey()).intValue()));

        SquadraDTOExtended squadraResult = SquadraMapper.squadraEntityToDtoExtendended(squadraEntity);
        squadraResult.setGiocatori(squadraDTOExtended.getGiocatori());
        return squadraResult;
    }

    @Override
    public List<SquadraDTOExtended> readAll() throws SQLException {
        List<Map<String, Object>> mapSquadra = jdbcTemplate.queryForList("select * from squadra");

        List<SquadraEntity> squadre = new ArrayList<>();
        for (Map<String, Object> map : mapSquadra) {
            SquadraEntity squadraEntity = new SquadraEntity();
            squadraEntity.setIdSquadra((Integer) map.get("idSquadra"));
            squadraEntity.setNome((String) map.get("nome"));
            squadraEntity.setColoriSociali((String) map.get("coloriSociali"));
            squadre.add(squadraEntity);
        }

        return squadre.stream()
                .map(SquadraMapper::squadraEntityToDtoExtendended)
                .collect(Collectors.toList());
    }

    @Override
    public SquadraDTOExtended findSquadraById(Integer idSquadra) throws SQLException {
        String s = "select * from squadra where id = ?";

        SquadraEntity squadraEntity = jdbcTemplate.queryForObject(s, new SquadraRowMapper(), idSquadra);

        return SquadraMapper.squadraEntityToDtoExtendended(squadraEntity);
    }

    @Override
    public boolean checkSquadraByName(String nomeSquadra) throws SQLException {
        String s = "select id from squadra where nome = ?";
        try {
            Integer id = jdbcTemplate.queryForObject(s, Integer.class, nomeSquadra);
            return id != null;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
        String sqlSquadraTorneo = "delete from squadra_torneo where id_squadra = :idSquadra";
        String sqlTifoseria = "delete from tifoseria where id_squadra = :idSquadra";
        String sqlGiocatore = "delete from giocatore where id_squadra = :idSquadra";
        String sqlSquadra = "delete from squadra where id = :idSquadra";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("idSquadra", id);

        jdbcTemplate.update(sqlSquadraTorneo, params);
        jdbcTemplate.update(sqlTifoseria, params);
        jdbcTemplate.update(sqlGiocatore, params);
        jdbcTemplate.update(sqlSquadra, params);
    }
}
