package it.reactive.academy.springMvc.repository.query;

import it.reactive.academy.springMvc.dto.extended.GiocatoreDTOExtended;
import it.reactive.academy.springMvc.dto.extended.SquadraDTOExtended;
import it.reactive.academy.springMvc.utility.mapper.GiocatoreMapper;
import it.reactive.academy.springMvc.utility.mapper.SquadraMapper;
import it.reactive.academy.springMvc.entity.GiocatoreEntity;
import it.reactive.academy.springMvc.entity.SquadraEntity;
import it.reactive.academy.springMvc.repository.dao.GiocatoreDao;
import it.reactive.academy.springMvc.utility.Costanti;
import it.reactive.academy.springMvc.utility.rowmapper.GiocatoreRowMapper;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

@Repository
@Profile(Costanti.TORNEO_DAO_SPRING_JDBC_QUERY)
public class GiocatoreDaoImpl implements GiocatoreDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public GiocatoreDaoImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public GiocatoreDTOExtended create(GiocatoreDTOExtended giocatoreDTOExtended) throws SQLException {
        GiocatoreEntity giocatoreEntity = GiocatoreMapper.giocatoreDtoExtendedToEntity(giocatoreDTOExtended);

        String s = "insert into giocatore (nome_cognome, id_squadra) values (:nomeCognome, :idSquadra)";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("nomeCognome", giocatoreEntity.getNomeCognome());
        params.addValue("idSquadra", giocatoreEntity.getSquadra().getIdSquadra());
        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(s, params, keyHolder);
        giocatoreEntity.setIdGiocatore((Integer) Objects.requireNonNull(keyHolder.getKeys().get("id")));

        return GiocatoreMapper.giocatoreEntityToDTOExtended(giocatoreEntity);
    }

    @Override
    public Set<GiocatoreDTOExtended> createAll(Set<GiocatoreDTOExtended> giocatoriDTOExtended) throws SQLException {
        Set<GiocatoreEntity> giocatoriModel = giocatoriDTOExtended.stream().map(GiocatoreMapper::giocatoreDtoExtendedToEntity).collect(Collectors.toSet());

        String s = "insert into giocatore (nome_cognome, id_squadra) values (:nomeCognome, :idSquadra)";

        MapSqlParameterSource params = new MapSqlParameterSource();
        for (GiocatoreEntity giocatoreEntity : giocatoriModel) {
            params.addValue("nomeCognome", giocatoreEntity.getNomeCognome());
            params.addValue("idSquadra", giocatoreEntity.getSquadra().getIdSquadra());
            KeyHolder keyHolder = new GeneratedKeyHolder();

            namedParameterJdbcTemplate.update(s, params, keyHolder);
            giocatoreEntity.setIdGiocatore((Integer) Objects.requireNonNull(keyHolder.getKeys().get("id")));
        }
        return giocatoriModel.stream().map(GiocatoreMapper::giocatoreEntityToDTOExtended).collect(Collectors.toSet());
    }

    @Override
    public Set<GiocatoreDTOExtended> readAllByTeam(SquadraDTOExtended squadraDTOExtended) throws SQLException {
        SquadraEntity squadraEntity = SquadraMapper.squadraDtoExtendedToEntity(squadraDTOExtended);

        String s = "select * from giocatore where id_squadra = :idSquadra";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("idSquadra", squadraEntity.getIdSquadra());

        List<GiocatoreEntity> giocatori = namedParameterJdbcTemplate.query(s, params, new GiocatoreRowMapper());
        return giocatori.stream().map(GiocatoreMapper::giocatoreEntityToDTOExtended).collect(Collectors.toSet());
    }

    public boolean checkByName(String nomeGiocatore) throws SQLException {
        String s = "select id from giocatore where nome_cognome = :nomeGiocatore";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("nomeGiocatore", nomeGiocatore);

        List<Integer> ids = namedParameterJdbcTemplate.query(s, params,
                (rs, rowNum) -> rs.getInt("id"));

        return !ids.isEmpty();
    }

    @Override
    public GiocatoreDTOExtended findGiocatoreById(Integer id) throws SQLException {
        GiocatoreEntity giocatoreEntity;
        String s = "select id, nome_cognome, numero_ammonizioni from giocatore where id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        List<GiocatoreEntity> giocatori =  namedParameterJdbcTemplate.query(s, params, new GiocatoreRowMapper());

        giocatoreEntity = giocatori.get(0);
        return GiocatoreMapper.giocatoreEntityToDTOExtended(giocatoreEntity);
    }

    @Override
    public void updateAmmonizioni(Integer id) throws SQLException {
        String s = "update giocatore set numero_ammonizioni = numero_ammonizioni + 1 where id = :id ";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        namedParameterJdbcTemplate.update(s, params);
    }
}

