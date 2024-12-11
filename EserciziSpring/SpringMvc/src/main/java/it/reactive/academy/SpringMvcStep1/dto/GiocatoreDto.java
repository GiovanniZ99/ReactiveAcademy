package it.reactive.academy.SpringMvcStep1.dto;

import javax.validation.constraints.NotBlank;

public class GiocatoreDTO {

    @NotBlank
    private String nomeCognome;

    public GiocatoreDTO() {
    }

    public String getNomeCognome() {
        return nomeCognome;
    }

    public void setNomeCognome(String nomeCognome) {
        this.nomeCognome = nomeCognome;
    }
}
