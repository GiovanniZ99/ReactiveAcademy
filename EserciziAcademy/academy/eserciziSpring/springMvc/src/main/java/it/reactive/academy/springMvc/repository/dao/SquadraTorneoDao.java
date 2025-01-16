package it.reactive.academy.springMvc.repository.dao;

import it.reactive.academy.springMvc.dto.extended.SquadraDTOExtended;
import it.reactive.academy.springMvc.dto.extended.SquadraTorneoDTOExtended;
import it.reactive.academy.springMvc.dto.extended.TorneoDTOExtended;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Set;

public interface SquadraTorneoDao {
    SquadraTorneoDTOExtended create(TorneoDTOExtended torneoDTOExtended, SquadraDTOExtended squadraDTOExtended) throws SQLException;

    Set<Integer> readAllTeamsById(Integer idTorneo) throws SQLException;

    LinkedHashMap<Integer, Set<Integer>> readAllTornei() throws SQLException;

    Set<Integer> readAllTorneoByIdSquadra(Integer idSquadra) throws SQLException;

    Set<TorneoDTOExtended> readAllTorneo() throws SQLException;
}
