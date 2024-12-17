package it.reactive.academy.springMvc.resource;

import java.util.Set;

public class Squadra {
    private Integer idSquadra;
    private String nome;
    private String coloriSociali;
    private Set<Giocatore> giocatori;
    private Tifoseria tifoseria;


    public Squadra() {}

    public Squadra(Integer idSquadra, String nome, String coloriSociali, Set<Giocatore> giocatori, Tifoseria tifoseria, Set<Torneo> tornei) {
        super();
        this.idSquadra = idSquadra;
        this.nome = nome;
        this.coloriSociali = coloriSociali;
        this.giocatori = giocatori;
        this.tifoseria = tifoseria;

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

    public Set<Giocatore> getGiocatori() {
        return giocatori;
    }

    public void setGiocatori(Set<Giocatore> giocatori) {
        this.giocatori = giocatori;
    }

    public Tifoseria getTifoseria() {
        return tifoseria;
    }

    public void setTifoseria(Tifoseria tifoseria) {
        this.tifoseria = tifoseria;
    }

}
