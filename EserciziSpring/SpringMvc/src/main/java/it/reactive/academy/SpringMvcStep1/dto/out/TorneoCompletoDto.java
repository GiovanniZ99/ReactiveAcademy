package it.reactive.academy.SpringMvcStep1.dto.out;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

public class TorneoCompletoDto {
    public static class GiocatoreDto {
        @NotBlank(message = "Il nome e cognome del giocatore non può essere assente")
        private String nomeCognome;

        @NotNull(message = "Il numero di ammonizioni non può essere nullo")
        private Integer numeroAmmonizioni;

        public GiocatoreDto(String nomeCognome, Integer numeroAmmonizioni) {
            this.nomeCognome = nomeCognome;
            this.numeroAmmonizioni = numeroAmmonizioni;
        }

        public @NotBlank(message = "Il nome e cognome del giocatore non può essere assente") String getNomeCognome() {
            return nomeCognome;
        }

        public void setNomeCognome(@NotBlank(message = "Il nome e cognome del giocatore non può essere assente") String nomeCognome) {
            this.nomeCognome = nomeCognome;
        }

        public @NotNull(message = "Il numero di ammonizioni non può essere nullo") Integer getNumeroAmmonizioni() {
            return numeroAmmonizioni;
        }

        public void setNumeroAmmonizioni(@NotNull(message = "Il numero di ammonizioni non può essere nullo") Integer numeroAmmonizioni) {
            this.numeroAmmonizioni = numeroAmmonizioni;
        }
    }

    public static class SquadraDto {
        @NotBlank(message = "Il nome della squadra non può essere assente")
        private String nomeSquadra;

        @NotBlank(message = "Il nome della tifoseria non può essere assente")
        private String nomeTifoseria;

        @NotEmpty(message = "La lista dei giocatori non può essere assente")
        private Set<GiocatoreDto> listaGiocatori;

        public SquadraDto(String nomeSquadra, String nomeTifoseria, Set<GiocatoreDto> listaGiocatori) {
            this.nomeSquadra = nomeSquadra;
            this.nomeTifoseria = nomeTifoseria;
            this.listaGiocatori = listaGiocatori;
        }

        public @NotBlank(message = "Il nome della squadra non può essere assente") String getNomeSquadra() {
            return nomeSquadra;
        }

        public void setNomeSquadra(@NotBlank(message = "Il nome della squadra non può essere assente") String nomeSquadra) {
            this.nomeSquadra = nomeSquadra;
        }

        public @NotBlank(message = "Il nome della tifoseria non può essere assente") String getNomeTifoseria() {
            return nomeTifoseria;
        }

        public void setNomeTifoseria(@NotBlank(message = "Il nome della tifoseria non può essere assente") String nomeTifoseria) {
            this.nomeTifoseria = nomeTifoseria;
        }

        public @NotEmpty(message = "La lista dei giocatori non può essere assente") Set<GiocatoreDto> getListaGiocatori() {
            return listaGiocatori;
        }

        public void setListaGiocatori(@NotEmpty(message = "La lista dei giocatori non può essere assente") Set<GiocatoreDto> listaGiocatori) {
            this.listaGiocatori = listaGiocatori;
        }
    }

    @NotBlank(message = "Il nome del torneo non può essere assente")
    private String nomeTorneo;

    @NotEmpty(message = "La lista delle squadre partecipanti non può essere assente")
    private Set<SquadraDto> squadrePartecipanti;

    public TorneoCompletoDto(String nomeTorneo, Set<SquadraDto> squadrePartecipanti) {
        this.nomeTorneo = nomeTorneo;
        this.squadrePartecipanti = squadrePartecipanti;
    }

    public @NotBlank(message = "Il nome del torneo non può essere assente") String getNomeTorneo() {
        return nomeTorneo;
    }

    public void setNomeTorneo(@NotBlank(message = "Il nome del torneo non può essere assente") String nomeTorneo) {
        this.nomeTorneo = nomeTorneo;
    }

    public @NotEmpty(message = "La lista delle squadre partecipanti non può essere assente") Set<SquadraDto> getSquadrePartecipanti() {
        return squadrePartecipanti;
    }

    public void setSquadrePartecipanti(@NotEmpty(message = "La lista delle squadre partecipanti non può essere assente") Set<SquadraDto> squadrePartecipanti) {
        this.squadrePartecipanti = squadrePartecipanti;
    }
}
