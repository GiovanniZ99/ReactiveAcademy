package it.reactive.academy.springMvc.model;

import it.reactive.academy.springMvc.resource.Squadra;

public class TifoseriaResource {
   private Integer idTifoseria;
   private String nomeTifoseria;
   private Squadra squadra;

    public TifoseriaResource(Integer idTifoseria, String nomeTifoseria, Squadra squadra) {
        this.idTifoseria = idTifoseria;
        this.nomeTifoseria = nomeTifoseria;
        this.squadra = squadra;
    }

    public TifoseriaResource() {
    }

    public Integer getIdTifoseria() {
        return idTifoseria;
    }

    public void setIdTifoseria(Integer idTifoseria) {
        this.idTifoseria = idTifoseria;
    }

    public String getNomeTifoseria() {
        return nomeTifoseria;
    }

    public void setNomeTifoseria(String nomeTifoseria) {
        this.nomeTifoseria = nomeTifoseria;
    }

    public Squadra getSquadra() {
        return squadra;
    }

    public void setSquadra(Squadra squadra) {
        this.squadra = squadra;
    }
}
