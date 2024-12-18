package it.reactive.academy.springMvc.repository.dao;

import it.reactive.academy.springMvc.dto.extended.SquadraDTOExtended;
import org.springframework.stereotype.Repository;

@Repository
public interface SquadraDao {
    SquadraDTOExtended create(SquadraDTOExtended squadraDTOExtended);
    SquadraDTOExtended read(int id);
    SquadraDTOExtended update(int id, SquadraDTOExtended squadraDTOExtended);
    SquadraDTOExtended delete(int id);
}
