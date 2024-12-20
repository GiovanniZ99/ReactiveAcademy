package it.reactive.academy.springMvc.repository.dao;

import it.reactive.academy.springMvc.dto.extended.GiocatoreDTOExtended;
import it.reactive.academy.springMvc.model.SquadraModel;

import java.sql.SQLException;
import java.util.Set;

public interface GiocatoreDao {
    GiocatoreDTOExtended createAll(GiocatoreDTOExtended giocatoreDTOExtended);


    Set<GiocatoreDTOExtended> readAll(SquadraModel squadraModel) throws SQLException;

    GiocatoreDTOExtended update(int id, GiocatoreDTOExtended giocatoreDTOExtended);

    GiocatoreDTOExtended delete(int id);
}
