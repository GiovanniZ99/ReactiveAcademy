package it.reactive.academy.springMvc.repository.dao;

import it.reactive.academy.springMvc.dto.extended.SquadraDTOExtended;

import java.sql.SQLException;
import java.util.List;

public interface SquadraDao {

    SquadraDTOExtended create(SquadraDTOExtended squadraDTOExtended) throws SQLException;

    List<SquadraDTOExtended> readAll() throws SQLException;

    SquadraDTOExtended findSquadraById(Integer idSquadra) throws SQLException;

    boolean checkSquadraByName(String nomeSquadra) throws SQLException;

    void delete(Integer id) throws SQLException;
}
