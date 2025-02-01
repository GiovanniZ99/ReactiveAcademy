package it.reactive.mongomvc.dto;

import javax.validation.constraints.NotBlank;

public class TifoseriaDTO {
    @NotBlank(message = "Il nome della tifoseria è obbligatorio.")
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

    public TifoseriaDTO() {
    }
}
