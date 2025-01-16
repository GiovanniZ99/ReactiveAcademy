package it.reactive.academy.springMvc.repository.query;

import it.reactive.academy.springMvc.dto.extended.SquadraDTOExtended;
import it.reactive.academy.springMvc.dto.extended.TifoseriaDTOExtended;
import it.reactive.academy.springMvc.utility.mapper.SquadraMapper;
import it.reactive.academy.springMvc.utility.mapper.TifoseriaMapper;
import it.reactive.academy.springMvc.entity.SquadraEntity;
import it.reactive.academy.springMvc.entity.TifoseriaEntity;
import it.reactive.academy.springMvc.repository.dao.TifoseriaDao;
import it.reactive.academy.springMvc.utility.Costanti;
import it.reactive.academy.springMvc.utility.rowmapper.TifoseriaRowMapper;
import org.springframework.context.annotation.Profile;
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
        TifoseriaEntity tifoseriaEntity = TifoseriaMapper.tifoseriaDtoExtendedToEntity(tifoseriaDTOExtended);

        String s = "insert into tifoseria (nome_tifoseria, id_squadra) values (:nomeTifoseria,:idSquadra)";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("nomeTifoseria", tifoseriaEntity.getNomeTifoseria());
        params.addValue("idSquadra", idSquadra);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(s, params, keyHolder);
        tifoseriaEntity.setIdTifoseria(((Integer) Objects.requireNonNull(keyHolder.getKeys().get("id"))));

        return TifoseriaMapper.tifoseriaEntityToDtoExtended(tifoseriaEntity);
    }

    @Override
    public TifoseriaDTOExtended readByTeam(SquadraDTOExtended squadraDTOExtended) throws SQLException {
        TifoseriaEntity tifoseriaResult;
        SquadraEntity squadraEntity = SquadraMapper.squadraDtoExtendedToEntity(squadraDTOExtended);

        String s = "select id, nome_tifoseria, id_squadra from tifoseria where id_squadra = :idSquadra";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("idSquadra", squadraEntity.getIdSquadra());

        tifoseriaResult = namedParameterJdbcTemplate.query(s, params, new TifoseriaRowMapper()).get(0);

        return TifoseriaMapper.tifoseriaEntityToDtoExtended(tifoseriaResult);
    }

    @Override
    public TifoseriaDTOExtended updateName(String nomeTifoseria, Integer idSquadra) throws SQLException {
        TifoseriaEntity tifoseriaEntity = new TifoseriaEntity();
        tifoseriaEntity.setNomeTifoseria(nomeTifoseria);
        tifoseriaEntity.setSquadra(new SquadraEntity());
        tifoseriaEntity.getSquadra().setIdSquadra(idSquadra);
        String s = "update tifoseria set nome_tifoseria = :nomeTifoseria where id_squadra = :idSquadra";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("nomeTifoseria", nomeTifoseria);
        params.addValue("idSquadra", idSquadra);
        namedParameterJdbcTemplate.update(s, params);

        return TifoseriaMapper.tifoseriaEntityToDtoExtended(tifoseriaEntity);
    }

}
