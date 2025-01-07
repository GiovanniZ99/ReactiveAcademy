package it.reactive.academy.springMvc.model;

public class TorneoModel {
    private Integer idTorneo;
    private String nomeTorneo;

    public TorneoModel(Integer idTorneo, String nomeTorneo) {
        this.idTorneo = idTorneo;
        this.nomeTorneo = nomeTorneo;
    }

    public TorneoModel() {

    }

    public Integer getIdTorneo() {
        return idTorneo;
    }

    public void setIdTorneo(Integer idTorneo) {
        this.idTorneo = idTorneo;
    }

    public String getNomeTorneo() {
        return nomeTorneo;
    }

    public void setNomeTorneo(String nomeTorneo) {
        this.nomeTorneo = nomeTorneo;
    }

}
