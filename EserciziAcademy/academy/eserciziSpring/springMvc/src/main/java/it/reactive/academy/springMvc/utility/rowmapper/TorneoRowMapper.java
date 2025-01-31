package it.reactive.academy.springMvc.utility.rowmapper;

import it.reactive.academy.springMvc.entity.TorneoEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TorneoRowMapper implements RowMapper<TorneoEntity> {
    @Override
    public TorneoEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        TorneoEntity torneoEntity = new TorneoEntity();
        torneoEntity.setIdTorneo(rs.getInt("id"));
        torneoEntity.setNomeTorneo(rs.getString("nome_torneo"));
        return torneoEntity;
    }
}
