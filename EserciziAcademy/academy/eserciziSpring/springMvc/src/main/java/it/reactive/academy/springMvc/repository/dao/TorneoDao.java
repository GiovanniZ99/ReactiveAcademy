package it.reactive.academy.springMvc.repository.dao;

import it.reactive.academy.springMvc.dto.extended.TorneoDTOExtended;

public interface TorneoDao {
    TorneoDTOExtended create(TorneoDTOExtended torneoDTOExtended);
    TorneoDTOExtended read(int id);
    TorneoDTOExtended update(int id, TorneoDTOExtended torneoDTOExtended);
    TorneoDTOExtended delete(int id);
}
