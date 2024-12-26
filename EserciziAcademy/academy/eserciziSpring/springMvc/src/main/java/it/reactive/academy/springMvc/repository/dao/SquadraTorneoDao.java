package it.reactive.academy.springMvc.repository.dao;

import it.reactive.academy.springMvc.dto.extended.SquadraTorneoDTOExtended;
import it.reactive.academy.springMvc.dto.extended.TorneoDTOExtended;

import java.sql.SQLException;
import java.util.Set;

public interface SquadraTorneoDao {
    SquadraTorneoDTOExtended create(Integer idTorneo, Integer idSquadra) throws SQLException;

    Set<Integer> readAllTeams(Integer idTorneo) throws SQLException;

    Set<TorneoDTOExtended> readAllTorneo() throws SQLException;
}
