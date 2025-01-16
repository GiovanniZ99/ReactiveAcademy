package it.reactive.academy.springMvc.utility.rowmapper;

import it.reactive.academy.springMvc.entity.SquadraEntity;
import it.reactive.academy.springMvc.entity.TifoseriaEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TifoseriaRowMapper implements RowMapper<TifoseriaEntity> {
    @Override
    public TifoseriaEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        TifoseriaEntity tifoseriaEntity = new TifoseriaEntity();
        tifoseriaEntity.setIdTifoseria(rs.getInt("id"));
        tifoseriaEntity.setNomeTifoseria(rs.getString("nome_tifoseria"));
        tifoseriaEntity.setSquadra(new SquadraEntity(rs.getInt("id_squadra"), null, null));
        return tifoseriaEntity;
    }
}