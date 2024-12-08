package it.reactive.academy.SpringMvcStep1.dto.in;

import javax.validation.constraints.NotBlank;

public class TifoseriaDto {
    @NotBlank(message = "Il nome della tifoseria non può essere assente")
    private String nomeTifoseria;
    @NotBlank(message = "L'id della squadra non può essere assente")
    private Integer idSquadra;

    public TifoseriaDto(String nomeTifoseria, Integer idSquadra) {
        this.nomeTifoseria = nomeTifoseria;
        this.idSquadra = idSquadra;
    }

    public @NotBlank(message = "Il nome della tifoseria non può essere assente") String getNomeTifoseria() {
        return nomeTifoseria;
    }

    public void setNomeTifoseria(@NotBlank(message = "Il nome della tifoseria non può essere assente") String nomeTifoseria) {
        this.nomeTifoseria = nomeTifoseria;
    }

    public @NotBlank(message = "L'id della squadra non può essere assente") Integer getIdSquadra() {
        return idSquadra;
    }

    public void setIdSquadra(@NotBlank(message = "L'id della squadra non può essere assente") Integer idSquadra) {
        this.idSquadra = idSquadra;
    }
}
