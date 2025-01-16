package it.reactive.academy.springMvc.repository.dao;

import it.reactive.academy.springMvc.dto.extended.TorneoDTOExtended;

import java.sql.SQLException;
import java.util.Set;

public interface TorneoDao {
    TorneoDTOExtended create(String nomeTorneo) throws SQLException;

    TorneoDTOExtended findById(Integer idTorneo) throws SQLException;

    Set<TorneoDTOExtended> findAll() throws SQLException;
    void delete(Integer id) throws SQLException;
}
