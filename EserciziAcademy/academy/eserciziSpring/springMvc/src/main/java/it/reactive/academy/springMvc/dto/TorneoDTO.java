package it.reactive.academy.SpringMvcStep1.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class TorneoDTO {
    @NotBlank
    @Size(min = 3, message = "Il nome del torneo deve avere almeno 3 caratteri")
    private String nomeTorneo;


    public TorneoDTO() {
    }

    public TorneoDTO(String nomeTorneo) {
        this.nomeTorneo = nomeTorneo;
    }

    public String getNomeTorneo() {
        return nomeTorneo;
    }

    public void setNomeTorneo(String nomeTorneo) {
        this.nomeTorneo = nomeTorneo;
    }


}
