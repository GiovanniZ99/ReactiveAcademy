package it.reactive.mongomvc.resource;

import java.util.Set;

public class SquadraResource {
    private Integer idSquadra;
    private String nome;
    private String coloriSociali;
    private Set<GiocatoreResource> giocatori;
    private TifoseriaResource tifoseriaResource;


    public SquadraResource() {}

    public SquadraResource(Integer idSquadra, String nome, String coloriSociali, Set<GiocatoreResource> giocatori, TifoseriaResource tifoseriaResource, Set<TorneoResource> tornei) {
        this.idSquadra = idSquadra;
        this.nome = nome;
        this.coloriSociali = coloriSociali;
        this.giocatori = giocatori;
        this.tifoseriaResource = tifoseriaResource;
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

    public Set<GiocatoreResource> getGiocatori() {
        return giocatori;
    }

    public void setGiocatori(Set<GiocatoreResource> giocatori) {
        this.giocatori = giocatori;
    }

    public TifoseriaResource getTifoseria() {
        return tifoseriaResource;
    }

    public void setTifoseria(TifoseriaResource tifoseriaResource) {
        this.tifoseriaResource = tifoseriaResource;
    }

}
