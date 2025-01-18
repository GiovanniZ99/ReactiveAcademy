package it.reactive.academy.springMvc.daotest;

import it.reactive.academy.springMvc.dto.extended.GiocatoreDTOExtended;
import it.reactive.academy.springMvc.dto.extended.SquadraDTOExtended;
import it.reactive.academy.springMvc.repository.dao.GiocatoreDao;
import it.reactive.academy.springMvc.repository.dao.SquadraDao;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public abstract class GiocatoreDaoTest {
    private final GiocatoreDao giocatoreDao;
    private final SquadraDao squadraDao;

    protected GiocatoreDaoTest(GiocatoreDao giocatoreDao, SquadraDao squadraDao) {
        this.giocatoreDao = giocatoreDao;
        this.squadraDao = squadraDao;
    }

    @Test
    void findGiocatoreById() throws SQLException {
        GiocatoreDTOExtended giocatore = giocatoreDao.findGiocatoreById(1);
        assertEquals("string", giocatore.getNomeCognome());
        assertNull(giocatore.getNumeroAmmonizioni());
    }
    @Test
    void updateAmmonizioni() throws SQLException{
        GiocatoreDTOExtended giocatore = new GiocatoreDTOExtended();
        giocatore.setIdGiocatore(1);
        giocatoreDao.updateAmmonizioni(1);
        assertEquals(1, giocatore.getNumeroAmmonizioni());
    }

    @Test
    void checkByName() throws SQLException {
        GiocatoreDTOExtended giocatoreEsistente = new GiocatoreDTOExtended();
        giocatoreEsistente.setNomeCognome("string");
        SquadraDTOExtended squadraDTOExtended = new SquadraDTOExtended();
        squadraDTOExtended.setNome("nome");
        squadraDTOExtended.setColoriSociali("nero");

        squadraDTOExtended.setIdSquadra(squadraDao.create(squadraDTOExtended).getIdSquadra());
        giocatoreEsistente.setSquadra(squadraDTOExtended);
        giocatoreDao.create(giocatoreEsistente);
        assertTrue(giocatoreDao.checkByName("string"));
    }
}
