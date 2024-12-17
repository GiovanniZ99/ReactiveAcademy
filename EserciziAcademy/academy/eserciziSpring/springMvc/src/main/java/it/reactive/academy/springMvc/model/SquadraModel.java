package it.reactive.academy.springMvc.model;

import it.reactive.academy.springMvc.resource.Giocatore;
import it.reactive.academy.springMvc.resource.Tifoseria;

import java.util.Set;

public class SquadraResource {
  private String nome;
  private String coloriSociali;
  private Set<Giocatore> giocatori;
  private Tifoseria tifoseria;

    public SquadraResource(String nome, String coloriSociali, Set<Giocatore> giocatori, Tifoseria tifoseria) {
        this.nome = nome;
        this.coloriSociali = coloriSociali;
        this.giocatori = giocatori;
        this.tifoseria = tifoseria;
    }

    public SquadraResource() {
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
