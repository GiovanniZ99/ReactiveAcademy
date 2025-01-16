package it.reactive.academy.springMvc.repository.query;

import it.reactive.academy.springMvc.dto.extended.SquadraDTOExtended;
import it.reactive.academy.springMvc.utility.mapper.SquadraMapper;
import it.reactive.academy.springMvc.entity.SquadraEntity;
import it.reactive.academy.springMvc.repository.dao.SquadraDao;
import it.reactive.academy.springMvc.utility.Costanti;
import it.reactive.academy.springMvc.utility.rowmapper.SquadraRowMapper;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
@Profile(Costanti.TORNEO_DAO_SPRING_JDBC_QUERY)
public class SquadraDaoImpl implements SquadraDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public SquadraDaoImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public SquadraDTOExtended create(SquadraDTOExtended squadraDTOExtended) throws SQLException {
        SquadraEntity squadraEntity = SquadraMapper.squadraDtoExtendedToEntity(squadraDTOExtended);

        String s = "insert into squadra (nome, colori_sociali) values (:nome, :coloriSociali)";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("nome", squadraEntity.getNome());
        params.addValue("coloriSociali", squadraEntity.getColoriSociali());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(s, params, keyHolder);
        squadraEntity.setIdSquadra((Integer) Objects.requireNonNull(keyHolder.getKeys().get("id")));

        SquadraDTOExtended squadraResult = SquadraMapper.squadraEntityToDtoExtendended(squadraEntity);
        squadraResult.setGiocatori(squadraDTOExtended.getGiocatori());
        return squadraResult;
    }

    @Override
    public List<SquadraDTOExtended> readAll() throws SQLException {
        List<SquadraEntity> listaSquadra;

        listaSquadra = namedParameterJdbcTemplate.query("select * from squadra",
                new SquadraRowMapper());

        return listaSquadra.stream()
                .map(SquadraMapper::squadraEntityToDtoExtendended)
                .collect(Collectors.toList());
    }

    @Override
    public SquadraDTOExtended findSquadraById(Integer idSquadra) throws SQLException {
        String s = "select * from squadra where id = :idSquadra";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("idSquadra", idSquadra);

        List<SquadraEntity> listaResult = namedParameterJdbcTemplate.query(s, params, new SquadraRowMapper());
        // perché ho dovuto usare per forza il metodo query da esercizio
        if (listaResult.isEmpty()) {
            listaResult.add(new SquadraEntity());
        }
        return SquadraMapper.squadraEntityToDtoExtendended(listaResult.get(0));
    }

    @Override
    public boolean checkSquadraByName(String nomeSquadra) throws SQLException {
        String s = "select id from squadra where nome = :nome";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("nome", nomeSquadra);
        List<Integer> listaId = namedParameterJdbcTemplate.query(s, params,
                (rs, rowNum) -> rs.getInt("id"));

        return !listaId.isEmpty();
    }

    @Override
    public void delete(Integer id) throws SQLException {
        String sqlSquadraTorneo = "delete from squadra_torneo where id_squadra = :idSquadra";
        String sqlTifoseria = "delete from tifoseria where id_squadra = :idSquadra";
        String sqlGiocatore = "delete from giocatore where id_squadra = :idSquadra";
        String sqlSquadra = "delete from squadra where id = :idSquadra";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("idSquadra", id);

        namedParameterJdbcTemplate.update(sqlSquadraTorneo, params);
        namedParameterJdbcTemplate.update(sqlTifoseria, params);
        namedParameterJdbcTemplate.update(sqlGiocatore, params);
        namedParameterJdbcTemplate.update(sqlSquadra, params);
    }
}
