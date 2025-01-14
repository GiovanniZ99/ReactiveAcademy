package it.reactive.academy.springMvc.utility.rowmapper;

import it.reactive.academy.springMvc.model.GiocatoreModel;
import it.reactive.academy.springMvc.model.SquadraModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class GiocatoreRowMapper implements RowMapper<GiocatoreModel> {

    @Override
    public GiocatoreModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        GiocatoreModel giocatoreModel = new GiocatoreModel();
        SquadraModel squadraModel = new SquadraModel();

        giocatoreModel.setIdGiocatore(rs.getInt("id"));
        giocatoreModel.setNomeCognome(rs.getString("nome_cognome"));
        giocatoreModel.setNumeroAmmonizioni(rs.getInt("numero_ammonizioni"));
        giocatoreModel.setSquadra(squadraModel);

        return giocatoreModel;
    }
}

