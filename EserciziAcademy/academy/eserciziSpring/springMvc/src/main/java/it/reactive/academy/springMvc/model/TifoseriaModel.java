package it.reactive.academy.springMvc.model;


public class TifoseriaModel {
   private Integer idTifoseria;
   private String nomeTifoseria;
   private SquadraModel squadra;

    public TifoseriaModel(Integer idTifoseria, String nomeTifoseria, SquadraModel squadra) {
        this.idTifoseria = idTifoseria;
        this.nomeTifoseria = nomeTifoseria;
        this.squadra = squadra;
    }

    public TifoseriaModel() {
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

    public SquadraModel getSquadra() {
        return squadra;
    }

    public void setSquadra(SquadraModel squadra) {
        this.squadra = squadra;
    }
}
