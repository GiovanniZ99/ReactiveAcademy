package it.reactive.academy.springMvc.serviceTest;

import it.reactive.academy.springMvc.dto.SquadraDTO;
import it.reactive.academy.springMvc.exception.SquadraGiaCensitaException;
import it.reactive.academy.springMvc.service.SquadraService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public abstract class SquadraServiceTest {
    private final SquadraService squadraService;

    public SquadraServiceTest(SquadraService squadraService) {
        this.squadraService = squadraService;
    }
    @Test
    void findSquadraEsistenteExceptionl() {
        SquadraDTO squadraGiaEsistente = new SquadraDTO();
        squadraGiaEsistente.setNome("string");
        assertThrows(SquadraGiaCensitaException.class, () -> squadraService.create(squadraGiaEsistente));
    }
}
