package it.reactive.academy.springMvc.repository.queryforx;

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
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.Objects;

@Repository
@Profile(Costanti.TORNEO_DAO_SPRING_JDBC_QUERY)
public class TifoseriaDaoImpl implements TifoseriaDao {

    private final JdbcTemplate jdbcTemplate;

    public TifoseriaDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public TifoseriaDTOExtended createWithTeam(TifoseriaDTOExtended tifoseriaDTOExtended, Integer idSquadra) throws SQLException {
        TifoseriaModel tifoseriaModel = TifoseriaMapper.tifoseriaDtoExtendedToModel(tifoseriaDTOExtended);

        String s = "insert into tifoseria (nome_tifoseria, id_squadra) values (?,?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(s, new Object[]{tifoseriaModel.getNomeTifoseria(), idSquadra}, keyHolder);
        tifoseriaModel.setIdTifoseria((Objects.requireNonNull(keyHolder.getKey()).intValue()));

        return TifoseriaMapper.tifoseriaModelToDtoExtended(tifoseriaModel);
    }

    @Override
    public TifoseriaDTOExtended readByTeam(SquadraDTOExtended squadraDTOExtended) throws SQLException {
        TifoseriaModel tifoseriaResult;
        SquadraModel squadraModel = SquadraMapper.squadraDtoExtendedToModel(squadraDTOExtended);

        String s = "select id, nome_tifoseria from tifoseria where id_squadra = ?";

        tifoseriaResult = jdbcTemplate.queryForObject(s, new BeanPropertyRowMapper<>(TifoseriaModel.class), squadraModel.getIdSquadra());

        return TifoseriaMapper.tifoseriaModelToDtoExtended(tifoseriaResult);
    }

    @Override
    public TifoseriaDTOExtended updateName(String nomeTifoseria, Integer idSquadra) throws SQLException {
        TifoseriaModel tifoseriaModel = new TifoseriaModel();
        tifoseriaModel.setNomeTifoseria(nomeTifoseria);
        tifoseriaModel.setSquadra(new SquadraModel());
        tifoseriaModel.getSquadra().setIdSquadra(idSquadra);
        String s = "update tifoseria set nome_tifoseria = ? where id_squadra = ?";

        jdbcTemplate.update(s, nomeTifoseria, idSquadra);

        return TifoseriaMapper.tifoseriaModelToDtoExtended(tifoseriaModel);
    }

}
