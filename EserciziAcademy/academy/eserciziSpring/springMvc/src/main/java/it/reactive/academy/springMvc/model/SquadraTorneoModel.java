package it.reactive.academy.springMvc.model;

public class SquadraTorneoModel {
    private Integer idSquadra;
    private Integer idTorneo;

    public SquadraTorneoModel(Integer idSquadra, Integer idTorneo) {
        this.idSquadra = idSquadra;
        this.idTorneo = idTorneo;
    }

    public SquadraTorneoModel() {
    }

    public Integer getIdSquadra() {
        return idSquadra;
    }

    public void setIdSquadra(Integer idSquadra) {
        this.idSquadra = idSquadra;
    }

    public Integer getIdTorneo() {
        return idTorneo;
    }

    public void setIdTorneo(Integer idTorneo) {
        this.idTorneo = idTorneo;
    }
}
