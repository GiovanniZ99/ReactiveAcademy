package it.reactive.academy.springMvc.utility.rowmapper;

import it.reactive.academy.springMvc.model.TorneoModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TorneoRowMapper implements RowMapper<TorneoModel> {
    @Override
    public TorneoModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        TorneoModel torneoModel = new TorneoModel();
        torneoModel.setIdTorneo(rs.getInt("id"));
        torneoModel.setNomeTorneo(rs.getString("nome_torneo"));
        return torneoModel;
    }
}
