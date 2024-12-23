package it.reactive.academy.springMvc.repository.dao;

import it.reactive.academy.springMvc.dto.extended.GiocatoreDTOExtended;
import it.reactive.academy.springMvc.dto.extended.SquadraDTOExtended;

import java.sql.SQLException;
import java.util.Set;

public interface GiocatoreDao {

    GiocatoreDTOExtended create (GiocatoreDTOExtended giocatoreDTOExtended) throws SQLException;

    Set<GiocatoreDTOExtended> createAll(Set<GiocatoreDTOExtended> giocatoreDTOExtended) throws SQLException;

    Set<GiocatoreDTOExtended> readAll() throws SQLException;
    Set<GiocatoreDTOExtended> readAllByTeam(SquadraDTOExtended squadraDTOExtended) throws SQLException;

    GiocatoreDTOExtended findGiocatoreById(Integer id) throws SQLException;

    boolean checkdByName(String input) throws SQLException;

    GiocatoreDTOExtended updateAmmonizioni(int id);

    GiocatoreDTOExtended delete(int id);
}
