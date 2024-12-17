package it.reactive.academy.springMvc.model;

import java.util.Set;

public class SquadraModel {
    private Integer idSquadra;
    private String nome;
    private String coloriSociali;
    private Set<GiocatoreModel> giocatori;
    private TifoseriaModel tifoseria;
    private TorneoModel torneo;

    public SquadraModel(Integer idSquadra, String nome, String coloriSociali,
                        Set<GiocatoreModel> giocatori, TifoseriaModel tifoseria, TorneoModel torneo) {
        this.idSquadra = idSquadra;
        this.nome = nome;
        this.coloriSociali = coloriSociali;
        this.giocatori = giocatori;
        this.tifoseria = tifoseria;
        this.torneo = torneo;
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

    public Set<GiocatoreModel> getGiocatori() {
        return giocatori;
    }

    public void setGiocatori(Set<GiocatoreModel> giocatori) {
        this.giocatori = giocatori;
    }

    public TifoseriaModel getTifoseria() {
        return tifoseria;
    }

    public void setTifoseria(TifoseriaModel tifoseria) {
        this.tifoseria = tifoseria;
    }

    public TorneoModel getTorneo() {
        return torneo;
    }

    public void setTorneo(TorneoModel torneo) {
        this.torneo = torneo;
    }
}
