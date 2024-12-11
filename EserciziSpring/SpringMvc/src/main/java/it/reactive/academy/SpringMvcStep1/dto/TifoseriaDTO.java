package it.reactive.academy.SpringMvcStep1.dto;

import javax.validation.constraints.NotBlank;

public class TifoseriaDTO {
    @NotBlank
    String nomeTifoseria;

    public String getNomeTifoseria() {
        return nomeTifoseria;
    }

    public void setNomeTifoseria(String nomeTifoseria) {
        this.nomeTifoseria = nomeTifoseria;
    }

    public TifoseriaDTO(String nomeTifoseria) {
        this.nomeTifoseria = nomeTifoseria;
    }
}
