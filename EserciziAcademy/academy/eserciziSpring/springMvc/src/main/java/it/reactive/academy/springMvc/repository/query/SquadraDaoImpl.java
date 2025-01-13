package it.reactive.academy.springMvc.repository.query;

import it.reactive.academy.springMvc.dto.extended.SquadraDTOExtended;
import it.reactive.academy.springMvc.utility.mapper.SquadraMapper;
import it.reactive.academy.springMvc.model.SquadraModel;
import it.reactive.academy.springMvc.repository.dao.SquadraDao;
import it.reactive.academy.springMvc.utility.Costanti;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
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
        SquadraModel squadraModel = SquadraMapper.squadraDtoExtendedToModel(squadraDTOExtended);

        String s =  "insert into squadra (nome, colori_sociali) values (:nome, :coloriSociali)";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("nome", squadraModel.getNome());
        params.addValue("coloriSociali", squadraModel.getColoriSociali());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(s, params, keyHolder);
        squadraModel.setIdSquadra((Integer) Objects.requireNonNull(keyHolder.getKeys().get("id")));

        SquadraDTOExtended squadraResult = SquadraMapper.squadraModelToDtoExtendended(squadraModel);
        squadraResult.setGiocatori(squadraDTOExtended.getGiocatori());
        return squadraResult;
    }

    @Override
    public List<SquadraDTOExtended> readAll() throws SQLException {
        List<SquadraModel> listaSquadra;

        MapSqlParameterSource emptyParams = new MapSqlParameterSource();
        listaSquadra = namedParameterJdbcTemplate.query("select * from squadra",
                emptyParams, new BeanPropertyRowMapper<>(SquadraModel.class));

        return listaSquadra.stream()
                .map(SquadraMapper::squadraModelToDtoExtendended)
                .collect(Collectors.toList());
    }

    @Override
    public SquadraDTOExtended findSquadraByOd(Integer idSquadra) throws SQLException {
        String s = "select * from squadra where id = :idSquadra";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("idSquadra", idSquadra);
      List<SquadraModel> listaResult =  namedParameterJdbcTemplate.query(s, params, new RowMapper<SquadraModel>() {
            @Override
            public SquadraModel mapRow(ResultSet rs, int rowNum) throws SQLException {
                SquadraModel squadraModel = new SquadraModel();
                squadraModel.setIdSquadra(rs.getInt("id"));
                squadraModel.setNome(rs.getString("nome"));
                squadraModel.setColoriSociali(rs.getString("colori_sociali"));
                return squadraModel;
            }
        });

        return SquadraMapper.squadraModelToDtoExtendended(listaResult.get(0));
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
