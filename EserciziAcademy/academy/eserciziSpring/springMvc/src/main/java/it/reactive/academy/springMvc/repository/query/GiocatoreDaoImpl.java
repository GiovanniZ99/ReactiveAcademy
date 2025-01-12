package it.reactive.academy.springMvc.repository.query;

import it.reactive.academy.springMvc.dto.extended.GiocatoreDTOExtended;
import it.reactive.academy.springMvc.dto.extended.SquadraDTOExtended;
import it.reactive.academy.springMvc.utility.mapper.GiocatoreMapper;
import it.reactive.academy.springMvc.utility.mapper.SquadraMapper;
import it.reactive.academy.springMvc.model.GiocatoreModel;
import it.reactive.academy.springMvc.model.SquadraModel;
import it.reactive.academy.springMvc.repository.dao.GiocatoreDao;
import it.reactive.academy.springMvc.utility.Costanti;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.*;
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
        GiocatoreModel giocatoreModel = GiocatoreMapper.giocatoreDtoExtendedToModel(giocatoreDTOExtended);

        String s = "insert into giocatore (nome_cognome, id_squadra) values (:nomeCognome, :idSquadra)";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("nomeCognome", giocatoreModel.getNomeCognome());
        params.addValue("idSquadra", giocatoreModel.getSquadra().getIdSquadra());
        KeyHolder keyHolder = new GeneratedKeyHolder();

       namedParameterJdbcTemplate.update(s, params, keyHolder);
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

        String s = "select * from giocatore where id_squadra = :idSquadra";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("idSquadra", squadraModel.getIdSquadra());

     return  namedParameterJdbcTemplate.query(s, params, new RowMapper<GiocatoreModel>() {
           @Override
           public GiocatoreModel mapRow(ResultSet rs, int rowNum) throws SQLException {
               GiocatoreModel giocatoreModel = new GiocatoreModel();
               giocatoreModel.setIdGiocatore(rs.getInt("id"));
               giocatoreModel.setNomeCognome(rs.getString("nome_cognome"));
               giocatoreModel.setNumeroAmmonizioni(rs.getInt("numero_ammonizioni"));
               giocatoreModel.setSquadra(squadraModel);
               return giocatoreModel;
           }
       }).stream().map(GiocatoreMapper::giocatoreModelToDTOExtended).collect(Collectors.toSet());
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
        GiocatoreModel giocatoreModel;
        String s = "select id, nome_cognome, numero_ammonizioni from giocatore where id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        List<GiocatoreModel> giocatori =  namedParameterJdbcTemplate.query(s, params, new RowMapper<GiocatoreModel>() {
            @Override
            public GiocatoreModel mapRow(ResultSet rs, int rowNum) throws SQLException {
                GiocatoreModel giocatoreModel = new GiocatoreModel();
                giocatoreModel.setIdGiocatore(rs.getInt("id"));
                giocatoreModel.setNomeCognome(rs.getString("nome_cognome"));
                giocatoreModel.setNumeroAmmonizioni(rs.getInt("numero_ammonizioni"));
                return giocatoreModel;
            }
        });

        giocatoreModel = giocatori.get(0);
        return GiocatoreMapper.giocatoreModelToDTOExtended(giocatoreModel);
    }

    @Override
    public void updateAmmonizioni(Integer id) throws SQLException {
        String s = "update giocatore set numero_ammonizioni = numero_ammonizioni + 1 where id = :id ";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        namedParameterJdbcTemplate.update(s, params);
    }
}

