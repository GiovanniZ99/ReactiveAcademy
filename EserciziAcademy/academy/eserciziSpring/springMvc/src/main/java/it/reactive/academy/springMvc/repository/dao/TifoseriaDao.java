package it.reactive.academy.springMvc.repository.dao;

import it.reactive.academy.springMvc.dto.extended.SquadraDTOExtended;
import it.reactive.academy.springMvc.dto.extended.TifoseriaDTOExtended;

import java.sql.SQLException;

public interface TifoseriaDao {
    TifoseriaDTOExtended createWithTeam(TifoseriaDTOExtended tifoseriaDTOExtended,
                                        Integer idSquadra) throws SQLException;

    TifoseriaDTOExtended readByTeam(SquadraDTOExtended squadraDTOExtended) throws SQLException;

    TifoseriaDTOExtended updateName(String name, Integer idSquadra) throws SQLException;
}
