package it.reactive.academy.springMvc.repository.dao;

import it.reactive.academy.springMvc.dto.extended.SquadraDTOExtended;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SquadraDao {
    SquadraDTOExtended create(SquadraDTOExtended squadraDTOExtended);
    List<SquadraDTOExtended> readAll(Boolean completo);
    SquadraDTOExtended update(int id, SquadraDTOExtended squadraDTOExtended);
    SquadraDTOExtended delete(int id);
}
