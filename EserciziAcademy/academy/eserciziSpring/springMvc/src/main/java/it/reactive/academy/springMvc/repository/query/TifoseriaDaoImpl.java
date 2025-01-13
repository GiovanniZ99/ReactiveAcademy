package it.reactive.academy.springMvc.repository.query;

import it.reactive.academy.springMvc.dto.extended.SquadraDTOExtended;
import it.reactive.academy.springMvc.dto.extended.TifoseriaDTOExtended;
import it.reactive.academy.springMvc.utility.mapper.SquadraMapper;
import it.reactive.academy.springMvc.utility.mapper.TifoseriaMapper;
import it.reactive.academy.springMvc.model.SquadraModel;
import it.reactive.academy.springMvc.model.TifoseriaModel;
import it.reactive.academy.springMvc.repository.dao.TifoseriaDao;
import it.reactive.academy.springMvc.utility.Costanti;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.Objects;

@Repository
@Profile(Costanti.TORNEO_DAO_SPRING_JDBC_QUERY)
public class TifoseriaDaoImpl implements TifoseriaDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public TifoseriaDaoImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public TifoseriaDTOExtended createWithTeam(TifoseriaDTOExtended tifoseriaDTOExtended, Integer idSquadra) throws SQLException {
        TifoseriaModel tifoseriaModel = TifoseriaMapper.tifoseriaDtoExtendedToModel(tifoseriaDTOExtended);

        String s = "insert into tifoseria (nome_tifoseria, id_squadra) values (:nomeTifoseria,:idSquadra)";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("nomeTifoseria", tifoseriaModel.getNomeTifoseria());
        params.addValue("idSquadra", idSquadra);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(s, params, keyHolder);
        tifoseriaModel.setIdTifoseria(((Integer) Objects.requireNonNull(keyHolder.getKeys().get("id"))));

        return TifoseriaMapper.tifoseriaModelToDtoExtended(tifoseriaModel);
    }

    @Override
    public TifoseriaDTOExtended readByTeam(SquadraDTOExtended squadraDTOExtended) throws SQLException {
        TifoseriaModel tifoseriaResult;
        SquadraModel squadraModel = SquadraMapper.squadraDtoExtendedToModel(squadraDTOExtended);

        String s = "select id, nome_tifoseria from tifoseria where id_squadra = :idSquadra";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("idSquadra", squadraModel.getIdSquadra());
        tifoseriaResult = namedParameterJdbcTemplate.query(s, params, new BeanPropertyRowMapper<>(TifoseriaModel.class)).get(0);

        return TifoseriaMapper.tifoseriaModelToDtoExtended(tifoseriaResult);
    }

    @Override
    public TifoseriaDTOExtended updateName(String nomeTifoseria, Integer idSquadra) throws SQLException {
        TifoseriaModel tifoseriaModel = new TifoseriaModel();
        tifoseriaModel.setNomeTifoseria(nomeTifoseria);
        tifoseriaModel.setSquadra(new SquadraModel());
        tifoseriaModel.getSquadra().setIdSquadra(idSquadra);
        String s = "update tifoseria set nome_tifoseria = :nomeTifoseria where id_squadra = :idSquadra";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("nomeTifoseria", nomeTifoseria);
        params.addValue("idSquadra", idSquadra);
        namedParameterJdbcTemplate.update(s, params);

        return TifoseriaMapper.tifoseriaModelToDtoExtended(tifoseriaModel);
    }

}
