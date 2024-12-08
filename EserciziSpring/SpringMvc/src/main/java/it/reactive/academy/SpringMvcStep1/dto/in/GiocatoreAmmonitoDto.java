package it.reactive.academy.SpringMvcStep1.dto.in;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class GiocatoreAmmonitoDto {
    @NotBlank(message = "L'id del giocatore non può essere assente")
    private Integer idGiocatore;

    public GiocatoreAmmonitoDto(Integer idGiocatore) {
        this.idGiocatore = idGiocatore;
    }

    public @NotBlank(message = "L'id del giocatore non può essere assente") Integer getIdGiocatore() {
        return idGiocatore;
    }

    public void setIdGiocatore(@NotBlank(message = "L'id del giocatore non può essere assente") Integer idGiocatore) {
        this.idGiocatore = idGiocatore;
    }
}
