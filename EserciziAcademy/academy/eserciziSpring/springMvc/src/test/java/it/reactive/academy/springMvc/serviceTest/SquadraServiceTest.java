package it.reactive.academy.springMvc.serviceTest;

import it.reactive.academy.springMvc.dto.SquadraDTO;
import it.reactive.academy.springMvc.exception.SquadraGiaCensitaException;
import it.reactive.academy.springMvc.service.SquadraService;
import it.reactive.academy.springMvc.utility.Costanti;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles(Costanti.TORNEO_DAO_JDBC_PREPAREDSTATEMENT)
class SquadraServiceTest {
    @Autowired
    private SquadraService squadraService;

    @Test
    void findSquadraEsistenteExceptionl() {
        SquadraDTO squadraGiaEsistente = new SquadraDTO();
        squadraGiaEsistente.setNome("string");
        assertThrows(SquadraGiaCensitaException.class, () -> squadraService.create(squadraGiaEsistente));
    }
}
