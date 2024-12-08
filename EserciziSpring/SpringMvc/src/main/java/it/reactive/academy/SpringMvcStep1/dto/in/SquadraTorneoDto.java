package it.reactive.academy.SpringMvcStep1.dto.in;

import javax.validation.constraints.NotBlank;

public class SquadraTorneoDto {
    @NotBlank(message = "L'id della squadra non può essere assente")
    private Integer idSquadra;
    @NotBlank(message = "L'id del torneo non può essere assente")
    private Integer idTorneo;

    public SquadraTorneoDto(Integer idSquadra, Integer idTorneo) {
        this.idSquadra = idSquadra;
        this.idTorneo = idTorneo;
    }

    public @NotBlank(message = "L'id della squadra non può essere assente") Integer getIdSquadra() {
        return idSquadra;
    }

    public void setIdSquadra(@NotBlank(message = "L'id della squadra non può essere assente") Integer idSquadra) {
        this.idSquadra = idSquadra;
    }

    public @NotBlank(message = "L'id del torneo non può essere assente") Integer getIdTorneo() {
        return idTorneo;
    }

    public void setIdTorneo(@NotBlank(message = "L'id del torneo non può essere assente") Integer idTorneo) {
        this.idTorneo = idTorneo;
    }
}
