package it.reactive.academy.springMvc.repository.dao;

import it.reactive.academy.springMvc.dto.extended.SquadraDTOExtended;

import java.sql.SQLException;
import java.util.List;

public interface SquadraDao {

    SquadraDTOExtended create(SquadraDTOExtended squadraDTOExtended) throws SQLException;

    List<SquadraDTOExtended> readTeamsAndPlayers() throws SQLException;

    List<SquadraDTOExtended> readAll() throws SQLException;

    boolean checkSquadraByName(String nomeSquadra) throws SQLException;

    SquadraDTOExtended findSquadraByOd(Integer idSquadra) throws SQLException;

    SquadraDTOExtended update(int id, SquadraDTOExtended squadraDTOExtended);

    SquadraDTOExtended delete(int id);
}
