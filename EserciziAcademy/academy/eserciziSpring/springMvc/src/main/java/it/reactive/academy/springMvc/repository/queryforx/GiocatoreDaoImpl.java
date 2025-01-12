package it.reactive.academy.springMvc.repository.queryforx;

import it.reactive.academy.springMvc.dto.extended.GiocatoreDTOExtended;
import it.reactive.academy.springMvc.dto.extended.SquadraDTOExtended;
import it.reactive.academy.springMvc.utility.mapper.GiocatoreMapper;
import it.reactive.academy.springMvc.utility.mapper.SquadraMapper;
import it.reactive.academy.springMvc.model.GiocatoreModel;
import it.reactive.academy.springMvc.model.SquadraModel;
import it.reactive.academy.springMvc.repository.dao.GiocatoreDao;
import it.reactive.academy.springMvc.utility.Costanti;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

@Repository
@Profile(Costanti.TORNEO_DAO_SPRING_JDBC_QUERY)
public class GiocatoreDaoImpl implements GiocatoreDao {

    private final JdbcTemplate jdbcTemplate;

    public GiocatoreDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public GiocatoreDTOExtended create(GiocatoreDTOExtended giocatoreDTOExtended) throws SQLException {
        GiocatoreModel giocatoreModel = GiocatoreMapper.giocatoreDtoExtendedToModel(giocatoreDTOExtended);

        String s = "insert into giocatore (nome_cognome, id_squadra) values (?,?)";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("nomeCognome", giocatoreModel.getNomeCognome());
        params.addValue("idSquadra", giocatoreModel.getSquadra().getIdSquadra());
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(s, keyHolder);
        giocatoreModel.setIdGiocatore(Objects.requireNonNull(keyHolder.getKey()).intValue());

        return GiocatoreMapper.giocatoreModelToDTOExtended(giocatoreModel);
    }

    @Override
    public Set<GiocatoreDTOExtended> createAll(Set<GiocatoreDTOExtended> giocatoriDTOExtended) throws SQLException {
        Set<GiocatoreDTOExtended> giocatoriResult = new HashSet<>();

        for (GiocatoreDTOExtended giocatoreDTOExtended : giocatoriDTOExtended) {
            GiocatoreDTOExtended giocatoreDTO = create(giocatoreDTOExtended);
            giocatoriResult.add(giocatoreDTO);
        }
        return giocatoriResult;
    }

    @Override
    public Set<GiocatoreDTOExtended> readAllByTeam(SquadraDTOExtended squadraDTOExtended) throws SQLException {
        SquadraModel squadraModel = SquadraMapper.squadraDtoExtendedToModel(squadraDTOExtended);

        String s = "select * from giocatore where id_squadra = ?";
        List<Map<String, Object>> mapGiocatori = jdbcTemplate.queryForList(s, squadraModel.getIdSquadra());
        Set<GiocatoreModel> giocatori = new HashSet<>();

        for (Map<String, Object> map : mapGiocatori) {
            GiocatoreModel giocatoreModel = new GiocatoreModel();
            giocatoreModel.setIdGiocatore((Integer) map.get("id"));
            giocatoreModel.setNomeCognome((String) map.get("nome_cognome"));
            giocatoreModel.setNumeroAmmonizioni((Integer) map.get("numero_ammonizioni"));
            giocatoreModel.setSquadra(squadraModel);
            giocatori.add(giocatoreModel);
        }
        return giocatori.stream().map(GiocatoreMapper::giocatoreModelToDTOExtended).collect(Collectors.toSet());
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
        GiocatoreModel giocatoreModel;
        String s = "select id, nome_cognome, numero_ammonizioni from giocatore where id = ?";

        giocatoreModel = jdbcTemplate.queryForObject(s, new BeanPropertyRowMapper<>(GiocatoreModel.class),id);

        return GiocatoreMapper.giocatoreModelToDTOExtended(giocatoreModel);
    }

    @Override
    public void updateAmmonizioni(Integer id) throws SQLException {
        String s = "update giocatore set numero_ammonizioni = numero_ammonizioni + 1 where id = :id ";

        jdbcTemplate.update(s, new Object[]{id});
    }
}

