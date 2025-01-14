package it.reactive.academy.springMvc.repository.querypsc;

import it.reactive.academy.springMvc.dto.extended.SquadraDTOExtended;
import it.reactive.academy.springMvc.dto.extended.TifoseriaDTOExtended;
import it.reactive.academy.springMvc.utility.mapper.SquadraMapper;
import it.reactive.academy.springMvc.utility.mapper.TifoseriaMapper;
import it.reactive.academy.springMvc.model.SquadraModel;
import it.reactive.academy.springMvc.model.TifoseriaModel;
import it.reactive.academy.springMvc.repository.dao.TifoseriaDao;
import it.reactive.academy.springMvc.utility.Costanti;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;

import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.lang.NonNull;
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
        TifoseriaModel tifoseriaModel = TifoseriaMapper.tifoseriaDtoExtendedToModel(tifoseriaDTOExtended);

        PreparedStatementCreator psc = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement("insert into tifoseria (nome_tifoseria, id_squadra) values (?, ?)", Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, tifoseriaModel.getNomeTifoseria());
                ps.setInt(2, idSquadra);
                return ps;
            }
        };
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(psc, keyHolder);

        tifoseriaModel.setIdTifoseria((Integer) Objects.requireNonNull(keyHolder.getKeys().get("id")));
        return TifoseriaMapper.tifoseriaModelToDtoExtended(tifoseriaModel);
    }

    @Override
    public TifoseriaDTOExtended readByTeam(SquadraDTOExtended squadraDTOExtended) throws SQLException {
        TifoseriaModel tifoseriaResult;
        SquadraModel squadraModel = SquadraMapper.squadraDtoExtendedToModel(squadraDTOExtended);

        ResultSetExtractor<TifoseriaModel> rse = new ResultSetExtractor<TifoseriaModel>() {
            @Override
            public TifoseriaModel extractData(ResultSet rs) throws SQLException, DataAccessException {
                TifoseriaModel tifoseriaModel = new TifoseriaModel();
                if (rs.next()) {
                    tifoseriaModel.setIdTifoseria(rs.getInt("id"));
                    tifoseriaModel.setNomeTifoseria(rs.getString("nome_tifoseria"));
                    return tifoseriaModel;
                }
                return null;
            }
        };
        String s = "select id, nome_tifoseria from tifoseria where id_squadra = ?";
        tifoseriaResult = jdbcTemplate.query(s, rse, squadraModel.getIdSquadra());
        if (tifoseriaResult == null) {
            return new TifoseriaDTOExtended();
        }
        return TifoseriaMapper.tifoseriaModelToDtoExtended(tifoseriaResult);
    }

    @Override
    public TifoseriaDTOExtended updateName(String nomeTifoseria, Integer idSquadra) throws SQLException {
        TifoseriaModel tifoseriaModel = new TifoseriaModel();
        tifoseriaModel.setNomeTifoseria(nomeTifoseria);
        tifoseriaModel.setSquadra(new SquadraModel());
        tifoseriaModel.getSquadra().setIdSquadra(idSquadra);

        PreparedStatementCreator psc = con -> {
          PreparedStatement ps =  con.prepareStatement("update tifoseria set nome_tifoseria = ? where id_squadra = ?");
            ps.setString(1, nomeTifoseria);
            ps.setInt(2, idSquadra);
            return ps;
        };

        jdbcTemplate.update(psc);
        return TifoseriaMapper.tifoseriaModelToDtoExtended(tifoseriaModel);
    }

}
