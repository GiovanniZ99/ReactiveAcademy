package it.reactive.academy.springMvc.model;


public class SquadraModel {
    private Integer idSquadra;
    private String nome;
    private String coloriSociali;

    public SquadraModel(Integer idSquadra, String nome, String coloriSociali) {
        this.idSquadra = idSquadra;
        this.nome = nome;
        this.coloriSociali = coloriSociali;
    }

    public SquadraModel() {
    }

    public Integer getIdSquadra() {
        return idSquadra;
    }

    public void setIdSquadra(Integer idSquadra) {
        this.idSquadra = idSquadra;
    }

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
