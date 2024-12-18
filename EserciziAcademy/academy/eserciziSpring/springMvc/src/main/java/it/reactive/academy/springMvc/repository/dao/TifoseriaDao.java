package it.reactive.academy.springMvc.repository.dao;

import it.reactive.academy.springMvc.dto.TifoseriaDTO;
import it.reactive.academy.springMvc.dto.extended.TifoseriaDTOExtended;

public interface TifoseriaDao {
    TifoseriaDTOExtended create(TifoseriaDTOExtended tifoseriaDTOExtended);
    TifoseriaDTOExtended read(int id);
    TifoseriaDTOExtended update(int id, TifoseriaDTOExtended tifoseriaDTOExtended);
    TifoseriaDTOExtended delete(int id);
}
