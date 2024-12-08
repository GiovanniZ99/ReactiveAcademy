package it.reactive.academy.SpringMvcStep1.dto.in;

import javax.validation.constraints.NotBlank;

public class TorneoDto {
    @NotBlank(message = "Il nome del torneo non può essere assente")
    private String nomeTorneo;

    public TorneoDto(String nomeTorneo) {
        this.nomeTorneo = nomeTorneo;
    }

    public @NotBlank(message = "Il nome del torneo non può essere assente") String getNomeTorneo() {
        return nomeTorneo;
    }

    public void setNomeTorneo(@NotBlank(message = "Il nome del torneo non può essere assente") String nomeTorneo) {
        this.nomeTorneo = nomeTorneo;
    }
}
