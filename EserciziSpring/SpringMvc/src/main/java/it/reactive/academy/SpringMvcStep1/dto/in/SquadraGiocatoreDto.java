package it.reactive.academy.SpringMvcStep1.dto.in;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import java.util.Set;

public class SquadraGiocatoreDto {
    public static class GiocatoreDto {
        @NotBlank(message = "Il nomeSquadra e il cognome non possono essere assenti")
        private String nomeCognome;
        @Min(0)
        private Integer numeroAmmonizioni;

        @NotBlank(message = "Il nomeSquadra non può essere assente")
        private String nomeSquadra;

        @NotBlank(message = "Non puoi lasciare i colori assenti")
        private String coloriSociali;

        public GiocatoreDto(String nomeCognome, Integer numeroAmmonizioni, String nome, String coloriSociali) {
            this.nomeCognome = nomeCognome;
            this.numeroAmmonizioni = numeroAmmonizioni;
            this.nomeSquadra = nome;
            this.coloriSociali = coloriSociali;
        }

        public @NotBlank(message = "Il nomeSquadra e il cognome non possono essere assenti") String getNomeCognome() {
            return nomeCognome;
        }

        public void setNomeCognome(@NotBlank(message = "Il nomeSquadra e il cognome non possono essere assenti") String nomeCognome) {
            this.nomeCognome = nomeCognome;
        }

        public @Min(0) Integer getNumeroAmmonizioni() {
            return numeroAmmonizioni;
        }

        public void setNumeroAmmonizioni(@Min(0) Integer numeroAmmonizioni) {
            this.numeroAmmonizioni = numeroAmmonizioni;
        }

        public @NotBlank(message = "Il nomeSquadra non può essere assente") String getNomeSquadra() {
            return nomeSquadra;
        }

        public void setNomeSquadra(@NotBlank(message = "Il nomeSquadra non può essere assente") String nomeSquadra) {
            this.nomeSquadra = nomeSquadra;
        }

        public @NotBlank(message = "Non puoi lasciare i colori assenti") String getColoriSociali() {
            return coloriSociali;
        }

        public void setColoriSociali(@NotBlank(message = "Non puoi lasciare i colori assenti") String coloriSociali) {
            this.coloriSociali = coloriSociali;
        }
    }

    @NotBlank(message = "Il nome non può essere assente")
    private String nome;

    @NotBlank(message = "Non puoi lasciare i colori assenti")
    private String coloriSociali;

    @NotEmpty(message = "I giocatori non possono essere assenti")
    private Set<GiocatoreDto> giocatori;

    public SquadraGiocatoreDto(String nome, String coloriSociali, Set<GiocatoreDto> giocatori) {
        this.nome = nome;
        this.coloriSociali = coloriSociali;
        this.giocatori = giocatori;
    }

    public @NotBlank(message = "Il nome non può essere assente") String getNome() {
        return nome;
    }

    public void setNome(@NotBlank(message = "Il nome non può essere assente") String nome) {
        this.nome = nome;
    }

    public @NotBlank(message = "Non puoi lasciare i colori assenti") String getColoriSociali() {
        return coloriSociali;
    }

    public void setColoriSociali(@NotBlank(message = "Non puoi lasciare i colori assenti") String coloriSociali) {
        this.coloriSociali = coloriSociali;
    }

    public @NotEmpty(message = "I giocatori non possono essere assenti") Set<GiocatoreDto> getGiocatori() {
        return giocatori;
    }

    public void setGiocatori(@NotEmpty(message = "I giocatori non possono essere assenti") Set<GiocatoreDto> giocatori) {
        this.giocatori = giocatori;
    }
}
