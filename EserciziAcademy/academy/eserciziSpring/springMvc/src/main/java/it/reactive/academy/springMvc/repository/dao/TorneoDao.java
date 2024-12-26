package it.reactive.academy.springMvc.repository.dao;

import it.reactive.academy.springMvc.dto.extended.TorneoDTOExtended;

import java.sql.SQLException;

public interface TorneoDao {
    TorneoDTOExtended create(String nomeTorneo) throws SQLException;

    TorneoDTOExtended findById(Integer idTorneo) throws SQLException;

    void delete(Integer id) throws SQLException;
}
