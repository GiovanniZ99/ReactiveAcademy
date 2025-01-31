package it.reactive.academy.springMvc.utility.rowmapper;

import it.reactive.academy.springMvc.entity.GiocatoreEntity;
import it.reactive.academy.springMvc.entity.SquadraEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class GiocatoreRowMapper implements RowMapper<GiocatoreEntity> {

    @Override
    public GiocatoreEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        GiocatoreEntity giocatoreEntity = new GiocatoreEntity();
        SquadraEntity squadraEntity = new SquadraEntity();

        giocatoreEntity.setIdGiocatore(rs.getInt("id"));
        giocatoreEntity.setNomeCognome(rs.getString("nome_cognome"));
        giocatoreEntity.setNumeroAmmonizioni(rs.getInt("numero_ammonizioni"));
        giocatoreEntity.setSquadra(squadraEntity);

        return giocatoreEntity;
    }
}

