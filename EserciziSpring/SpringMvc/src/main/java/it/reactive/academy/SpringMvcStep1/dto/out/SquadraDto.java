package it.reactive.academy.SpringMvcStep1.dto.out;

import it.reactive.academy.SpringMvcStep1.model.Tifoseria;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

public class SquadraDto {

    private static class GiocatoreDto {
        private Integer idGiocatore;
        private String nomeCognome;

        public GiocatoreDto(Integer idGiocatore, String nomeCognome) {
            this.idGiocatore = idGiocatore;
            this.nomeCognome = nomeCognome;
        }

        public Integer getIdGiocatore() {
            return idGiocatore;
        }

        public void setIdGiocatore(Integer idGiocatore) {
            this.idGiocatore = idGiocatore;
        }

        public String getNomeCognome() {
            return nomeCognome;
        }

        public void setNomeCognome(String nomeCognome) {
            this.nomeCognome = nomeCognome;
        }
    }

    @NotBlank(message = "Il nome della squadra non può essere vuoto o nullo")
    private String nome;

    @NotBlank(message = "I colori sociali non possono essere vuoti o nulli")
    private String coloriSociali;

    @NotNull(message = "La tifoseria non può essere nulla")
    private Tifoseria tifoseria;

    private Set<GiocatoreDto> listaGiocatoriDto;

    public SquadraDto(String nome, String coloriSociali, Tifoseria tifoseria, Set<GiocatoreDto> listaGiocatoriDto) {
        this.nome = nome;
        this.coloriSociali = coloriSociali;
        this.tifoseria = tifoseria;
        this.listaGiocatoriDto = listaGiocatoriDto;
    }

    public @NotBlank(message = "Il nome della squadra non può essere vuoto o nullo") String getNome() {
        return nome;
    }

    public void setNome(@NotBlank(message = "Il nome della squadra non può essere vuoto o nullo") String nome) {
        this.nome = nome;
    }

    public @NotBlank(message = "I colori sociali non possono essere vuoti o nulli") String getColoriSociali() {
        return coloriSociali;
    }

    public void setColoriSociali(@NotBlank(message = "I colori sociali non possono essere vuoti o nulli") String coloriSociali) {
        this.coloriSociali = coloriSociali;
    }

    public @NotNull(message = "La tifoseria non può essere nulla") Tifoseria getTifoseria() {
        return tifoseria;
    }

    public void setTifoseria(@NotNull(message = "La tifoseria non può essere nulla") Tifoseria tifoseria) {
        this.tifoseria = tifoseria;
    }

    public Set<GiocatoreDto> getListaGiocatoriDto() {
        return listaGiocatoriDto;
    }

    public void setListaGiocatoriDto(Set<GiocatoreDto> listaGiocatoriDto) {
        this.listaGiocatoriDto = listaGiocatoriDto;
    }
}
