package it.reactive.academy.SpringMvcStep1.dto.in;

import javax.validation.constraints.NotBlank;

public class GiocatoreSquadraDto {
    @NotBlank(message = "L'id non può essere assente")
    private Integer idSquadra;
    @NotBlank(message = "Il nome e il cognome del giocatore non possono essere assenti")
    private String nomeCognome;

    private Integer numeroAmmonizioni;

    public GiocatoreSquadraDto(Integer idSquadra, String nomeCognome, Integer numeroAmmonizioni) {
        this.idSquadra = idSquadra;
        this.nomeCognome = nomeCognome;
        this.numeroAmmonizioni = numeroAmmonizioni;
    }

    public @NotBlank(message = "L'id non può essere assente") Integer getIdSquadra() {
        return idSquadra;
    }

    public void setIdSquadra(@NotBlank(message = "L'id non può essere assente") Integer idSquadra) {
        this.idSquadra = idSquadra;
    }

    public @NotBlank(message = "Il nome e il cognome del giocatore non possono essere assenti") String getNomeCognome() {
        return nomeCognome;
    }

    public void setNomeCognome(@NotBlank(message = "Il nome e il cognome del giocatore non possono essere assenti") String nomeCognome) {
        this.nomeCognome = nomeCognome;
    }

    public Integer getNumeroAmmonizioni() {
        return numeroAmmonizioni;
    }

    public void setNumeroAmmonizioni(Integer numeroAmmonizioni) {
        this.numeroAmmonizioni = numeroAmmonizioni;
    }
}
