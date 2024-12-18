package it.reactive.academy.springMvc.repository.dao;

import it.reactive.academy.springMvc.dto.extended.GiocatoreDTOExtended;

public interface GiocatoreDao {
    GiocatoreDTOExtended create(GiocatoreDTOExtended giocatoreDTOExtended);
    GiocatoreDTOExtended read(int id);
    GiocatoreDTOExtended update(int id, GiocatoreDTOExtended giocatoreDTOExtended);
    GiocatoreDTOExtended delete(int id);
}
