package it.reactive.academy.SpringMvcStep1.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SquadraDTO {

    @NotNull
    @Size(min = 3, max = 20)
    private String nome;
    private String coloriSociali;


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getColoriSociali() {
        return coloriSociali;
    }

    public void setColoriSociali(String coloriSociali) {
        this.coloriSociali = coloriSociali;
    }

}
