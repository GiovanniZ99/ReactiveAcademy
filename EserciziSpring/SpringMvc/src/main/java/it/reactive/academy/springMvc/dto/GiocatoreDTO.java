package it.reactive.academy.SpringMvcStep1.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class GiocatoreDTO {

    @NotBlank
    @Size(min = 3, max = 20)
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
