package it.reactive.academy.springMvc.utility.rowmapper;

import it.reactive.academy.springMvc.entity.SquadraEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SquadraRowMapper implements RowMapper<SquadraEntity> {

    @Override
    public SquadraEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        SquadraEntity squadraEntity = new SquadraEntity();

        squadraEntity.setIdSquadra(rs.getInt("id"));
        squadraEntity.setNome(rs.getString("nome"));
        squadraEntity.setColoriSociali(rs.getString("colori_sociali"));
        return squadraEntity;
    }
}
