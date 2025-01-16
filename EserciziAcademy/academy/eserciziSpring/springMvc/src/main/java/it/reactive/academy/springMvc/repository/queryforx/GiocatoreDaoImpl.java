package it.reactive.academy.springMvc.repository.queryforx;

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
public class GiocatoreDaoImpl implements GiocatoreDao {

    private final JdbcTemplate jdbcTemplate;

    public GiocatoreDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public GiocatoreDTOExtended create(GiocatoreDTOExtended giocatoreDTOExtended) throws SQLException {
        GiocatoreEntity giocatoreEntity = GiocatoreMapper.giocatoreDtoExtendedToEntity(giocatoreDTOExtended);

        String s = "insert into giocatore (nome_cognome, id_squadra) values (?,?)";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("nomeCognome", giocatoreEntity.getNomeCognome());
        params.addValue("idSquadra", giocatoreEntity.getSquadra().getIdSquadra());
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(s, keyHolder);
        giocatoreEntity.setIdGiocatore(Objects.requireNonNull(keyHolder.getKey()).intValue());

        return GiocatoreMapper.giocatoreEntityToDTOExtended(giocatoreEntity);
    }

    @Override
    public Set<GiocatoreDTOExtended> createAll(Set<GiocatoreDTOExtended> giocatoriDTOExtended) throws SQLException {

        Set<GiocatoreEntity> giocatoriModel = giocatoriDTOExtended.stream().map(GiocatoreMapper::giocatoreDtoExtendedToEntity).collect(Collectors.toSet());

        String s = "insert into giocatore (nome_cognome, id_squadra) values (?,?)";

        MapSqlParameterSource params = new MapSqlParameterSource();
        for (GiocatoreEntity giocatoreEntity : giocatoriModel) {
            params.addValue("nomeCognome", giocatoreEntity.getNomeCognome());
            params.addValue("idSquadra", giocatoreEntity.getSquadra().getIdSquadra());
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(s, keyHolder);
            giocatoreEntity.setIdGiocatore(Objects.requireNonNull(keyHolder.getKey()).intValue());
        }
        return giocatoriModel.stream().map(GiocatoreMapper::giocatoreEntityToDTOExtended).collect(Collectors.toSet());
    }

    @Override
    public Set<GiocatoreDTOExtended> readAllByTeam(SquadraDTOExtended squadraDTOExtended) throws SQLException {
        SquadraEntity squadraEntity = SquadraMapper.squadraDtoExtendedToEntity(squadraDTOExtended);

        String s = "select * from giocatore where id_squadra = ?";
        List<Map<String, Object>> mapGiocatori = jdbcTemplate.queryForList(s, squadraEntity.getIdSquadra());
        Set<GiocatoreEntity> giocatori = new HashSet<>();

        for (Map<String, Object> map : mapGiocatori) {
            GiocatoreEntity giocatoreEntity = new GiocatoreEntity();
            giocatoreEntity.setIdGiocatore((Integer) map.get("id"));
            giocatoreEntity.setNomeCognome((String) map.get("nome_cognome"));
            giocatoreEntity.setNumeroAmmonizioni((Integer) map.get("numero_ammonizioni"));
            giocatoreEntity.setSquadra(squadraEntity);
            giocatori.add(giocatoreEntity);
        }
        return giocatori.stream().map(GiocatoreMapper::giocatoreEntityToDTOExtended).collect(Collectors.toSet());
    }

    public boolean checkByName(String nomeGiocatore) throws SQLException {
        String s = "select id from giocatore where nome_cognome = ?";
        try {
            Integer id = jdbcTemplate.queryForObject(s, Integer.class, nomeGiocatore);
            return id != null;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

    @Override
    public GiocatoreDTOExtended findGiocatoreById(Integer id) throws SQLException {
        GiocatoreEntity giocatoreEntity;
        String s = "select id, nome_cognome, numero_ammonizioni from giocatore where id = ?";

        giocatoreEntity = jdbcTemplate.queryForObject(s, new GiocatoreRowMapper(),id);

        return GiocatoreMapper.giocatoreEntityToDTOExtended(giocatoreEntity);
    }

    @Override
    public void updateAmmonizioni(Integer id) throws SQLException {
        String s = "update giocatore set numero_ammonizioni = numero_ammonizioni + 1 where id = :id ";

        jdbcTemplate.update(s, new Object[]{id});
    }
}

