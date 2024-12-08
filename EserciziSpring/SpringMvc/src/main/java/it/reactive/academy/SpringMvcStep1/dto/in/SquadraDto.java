package it.reactive.academy.SpringMvcStep1.dto.in;

import javax.validation.constraints.NotBlank;

public class SquadraDto {
    @NotBlank(message = "Il nome non può essere assente")
    private String nome;

    @NotBlank(message = "Non puoi lasciare i colori assenti")
    private String coloriSociali;

    public SquadraDto(String nome, String coloriSociali) {
        this.nome = nome;
        this.coloriSociali = coloriSociali;
    }

    public @NotBlank(message = "Il nome non può essere assente") String getNome() {
        return nome;
    }

    public void setNome(@NotBlank(message = "Il nome non può essere assente") String nome) {
        this.nome = nome;
    }

    public @NotBlank(message = "Non puoi lasciare i colori assenti") String getColoriSociali() {
        return coloriSociali;
    }

    public void setColoriSociali(@NotBlank(message = "Non puoi lasciare i colori assenti") String coloriSociali) {
        this.coloriSociali = coloriSociali;
    }
}
