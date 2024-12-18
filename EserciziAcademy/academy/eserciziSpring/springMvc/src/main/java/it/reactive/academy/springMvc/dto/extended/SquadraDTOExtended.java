package it.reactive.academy.springMvc.dto.extended;

import java.util.Set;

public class SquadraDTOExtended {
    private Integer idSquadra;
    private String nome;
    private String coloriSociali;
    private Set<GiocatoreDTOExtended> giocatori;
    private TifoseriaDTOExtended tifoseria;
    private TorneoDTOExtended torneo;

    public SquadraDTOExtended(){}

    public SquadraDTOExtended(Integer idSquadra, String nome, String coloriSociali, Set<GiocatoreDTOExtended> giocatori,
                              TifoseriaDTOExtended tifoseria, TorneoDTOExtended torneo) {
        this.idSquadra = idSquadra;
        this.nome = nome;
        this.coloriSociali = coloriSociali;
        this.giocatori = giocatori;
        this.tifoseria = tifoseria;
        this.torneo = torneo;
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

    public Set<GiocatoreDTOExtended> getGiocatori() {
        return giocatori;
    }

    public void setGiocatori(Set<GiocatoreDTOExtended> giocatori) {
        this.giocatori = giocatori;
    }

    public TifoseriaDTOExtended getTifoseria() {
        return tifoseria;
    }

    public void setTifoseria(TifoseriaDTOExtended tifoseria) {
        this.tifoseria = tifoseria;
    }

    public TorneoDTOExtended getTorneo() {
        return torneo;
    }

    public void setTorneo(TorneoDTOExtended torneo) {
        this.torneo = torneo;
    }
}
