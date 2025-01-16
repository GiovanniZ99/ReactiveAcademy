package it.reactive.academy.springMvc.repository.querypsc;

import it.reactive.academy.springMvc.dto.extended.SquadraDTOExtended;
import it.reactive.academy.springMvc.dto.extended.TifoseriaDTOExtended;
import it.reactive.academy.springMvc.utility.mapper.SquadraMapper;
import it.reactive.academy.springMvc.utility.mapper.TifoseriaMapper;
import it.reactive.academy.springMvc.entity.SquadraEntity;
import it.reactive.academy.springMvc.entity.TifoseriaEntity;
import it.reactive.academy.springMvc.repository.dao.TifoseriaDao;
import it.reactive.academy.springMvc.utility.Costanti;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;

import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.Objects;

@Repository
@Profile(Costanti.TORNEO_DAO_SPRING_JDBC_QUERY_PSC)
public class TifoseriaDaoImpl implements TifoseriaDao {

    private final JdbcTemplate jdbcTemplate;

    public TifoseriaDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public TifoseriaDTOExtended createWithTeam(TifoseriaDTOExtended tifoseriaDTOExtended, Integer idSquadra) throws SQLException {
        TifoseriaEntity tifoseriaEntity = TifoseriaMapper.tifoseriaDtoExtendedToEntity(tifoseriaDTOExtended);

        PreparedStatementCreator psc = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement("insert into tifoseria (nome_tifoseria, id_squadra) values (?, ?)", Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, tifoseriaEntity.getNomeTifoseria());
                ps.setInt(2, idSquadra);
                return ps;
            }
        };
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(psc, keyHolder);

        tifoseriaEntity.setIdTifoseria((Integer) Objects.requireNonNull(keyHolder.getKeys().get("id")));
        return TifoseriaMapper.tifoseriaEntityToDtoExtended(tifoseriaEntity);
    }

    @Override
    public TifoseriaDTOExtended readByTeam(SquadraDTOExtended squadraDTOExtended) throws SQLException {
        TifoseriaEntity tifoseriaResult;
        SquadraEntity squadraEntity = SquadraMapper.squadraDtoExtendedToEntity(squadraDTOExtended);

        ResultSetExtractor<TifoseriaEntity> rse = new ResultSetExtractor<TifoseriaEntity>() {
            @Override
            public TifoseriaEntity extractData(ResultSet rs) throws SQLException, DataAccessException {
                TifoseriaEntity tifoseriaEntity = new TifoseriaEntity();
                if (rs.next()) {
                    tifoseriaEntity.setIdTifoseria(rs.getInt("id"));
                    tifoseriaEntity.setNomeTifoseria(rs.getString("nome_tifoseria"));
                    return tifoseriaEntity;
                }
                return null;
            }
        };
        String s = "select id, nome_tifoseria, id_squadra from tifoseria where id_squadra = ?";
        tifoseriaResult = jdbcTemplate.query(s, rse, squadraEntity.getIdSquadra());
        if (tifoseriaResult == null) {
            return new TifoseriaDTOExtended();
        }
        return TifoseriaMapper.tifoseriaEntityToDtoExtended(tifoseriaResult);
    }

    @Override
    public TifoseriaDTOExtended updateName(String nomeTifoseria, Integer idSquadra) throws SQLException {
        TifoseriaEntity tifoseriaEntity = new TifoseriaEntity();
        tifoseriaEntity.setNomeTifoseria(nomeTifoseria);
        tifoseriaEntity.setSquadra(new SquadraEntity());
        tifoseriaEntity.getSquadra().setIdSquadra(idSquadra);

        PreparedStatementCreator psc = con -> {
          PreparedStatement ps =  con.prepareStatement("update tifoseria set nome_tifoseria = ? where id_squadra = ?");
            ps.setString(1, nomeTifoseria);
            ps.setInt(2, idSquadra);
            return ps;
        };

        jdbcTemplate.update(psc);
        return TifoseriaMapper.tifoseriaEntityToDtoExtended(tifoseriaEntity);
    }

}
