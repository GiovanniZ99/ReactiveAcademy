package it.reactive.academy.SpringMvcStep1.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class SquadraDiGiocatoriDTO {

    @NotNull
    @Size(min = 3, max = 20)
    private String nome;
    @NotBlank
    private String coloriSociali;
    @NotEmpty
    @Valid
    private List<GiocatoreDTO> listaGiocatori;

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

    public List<GiocatoreDTO> getListaGiocatori() {
        return listaGiocatori;
    }

    public void setListaGiocatori(List<GiocatoreDTO> listaGiocatori) {
        this.listaGiocatori = listaGiocatori;
    }
}
