package it.reactive.academy.springMvc.repository.dao;

import it.reactive.academy.springMvc.dto.extended.SquadraDTOExtended;
import it.reactive.academy.springMvc.dto.extended.SquadraTorneoDTOExtended;
import it.reactive.academy.springMvc.dto.extended.TorneoDTOExtended;

import java.sql.SQLException;
import java.util.Set;

public interface SquadraTorneoDao {
    SquadraTorneoDTOExtended create(TorneoDTOExtended torneoDTOExtended, SquadraDTOExtended squadraDTOExtended) throws SQLException;

    Set<SquadraDTOExtended> readAllTeamsById(TorneoDTOExtended torneoDTOExtended) throws SQLException;

    Set<TorneoDTOExtended> readAllTorneoByIdSquadra(SquadraDTOExtended squadraDTOExtended) throws SQLException;

    Set<TorneoDTOExtended> readAllTorneo() throws SQLException;
}
