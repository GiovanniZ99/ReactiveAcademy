package it.reactive.academy.springMvc.repository.queryforx;

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
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

@Repository
@Profile(Costanti.TORNEO_DAO_SPRING_JDBC_QUERY_FOR_X)
public class TifoseriaDaoImpl implements TifoseriaDao {

    private final JdbcTemplate jdbcTemplate;

    public TifoseriaDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public TifoseriaDTOExtended createWithTeam(TifoseriaDTOExtended tifoseriaDTOExtended, Integer idSquadra) throws SQLException {
        TifoseriaEntity tifoseriaEntity = TifoseriaMapper.tifoseriaDtoExtendedToEntity(tifoseriaDTOExtended);

        String s = "insert into tifoseria (nome_tifoseria, id_squadra) values (?,?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(s, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, tifoseriaEntity.getNomeTifoseria());
            ps.setInt(2, idSquadra);
            return ps;
        }, keyHolder);
        tifoseriaEntity.setIdTifoseria(((Integer) keyHolder.getKeyList().get(0).get("id")));

        return TifoseriaMapper.tifoseriaEntityToDtoExtended(tifoseriaEntity);
    }

    @Override
    public TifoseriaDTOExtended readByTeam(SquadraDTOExtended squadraDTOExtended) throws SQLException {
        TifoseriaEntity tifoseriaResult;
        SquadraEntity squadraEntity = SquadraMapper.squadraDtoExtendedToEntity(squadraDTOExtended);

        String s = "select id, nome_tifoseria, id_squadra from tifoseria where id_squadra = ?";

        tifoseriaResult = jdbcTemplate.queryForObject(s, new TifoseriaRowMapper(), squadraEntity.getIdSquadra());
        if(tifoseriaResult == null){
            tifoseriaResult = new TifoseriaEntity();
        }
        return TifoseriaMapper.tifoseriaEntityToDtoExtended(tifoseriaResult);
    }

    @Override
    public TifoseriaDTOExtended updateName(String nomeTifoseria, Integer idSquadra) throws SQLException {
        TifoseriaEntity tifoseriaEntity = new TifoseriaEntity();
        tifoseriaEntity.setNomeTifoseria(nomeTifoseria);
        tifoseriaEntity.setSquadra(new SquadraEntity());
        tifoseriaEntity.getSquadra().setIdSquadra(idSquadra);
        String s = "update tifoseria set nome_tifoseria = ? where id_squadra = ?";

        jdbcTemplate.update(s, nomeTifoseria, idSquadra);

        return TifoseriaMapper.tifoseriaEntityToDtoExtended(tifoseriaEntity);
    }

}
