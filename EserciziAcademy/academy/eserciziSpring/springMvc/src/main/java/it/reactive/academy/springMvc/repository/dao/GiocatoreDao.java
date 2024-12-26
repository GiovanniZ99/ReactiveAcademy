package it.reactive.academy.springMvc.repository.dao;

import it.reactive.academy.springMvc.dto.extended.GiocatoreDTOExtended;
import it.reactive.academy.springMvc.dto.extended.SquadraDTOExtended;

import java.sql.SQLException;
import java.util.Set;

public interface GiocatoreDao {

    GiocatoreDTOExtended create (GiocatoreDTOExtended giocatoreDTOExtended) throws SQLException;

    Set<GiocatoreDTOExtended> createAll(Set<GiocatoreDTOExtended> giocatoreDTOExtended) throws SQLException;

    Set<GiocatoreDTOExtended> readAllByTeam(SquadraDTOExtended squadraDTOExtended) throws SQLException;

    GiocatoreDTOExtended findGiocatoreById(Integer id) throws SQLException;

    boolean checkdByName(String input) throws SQLException;

    void updateAmmonizioni(Integer id) throws SQLException;

}
