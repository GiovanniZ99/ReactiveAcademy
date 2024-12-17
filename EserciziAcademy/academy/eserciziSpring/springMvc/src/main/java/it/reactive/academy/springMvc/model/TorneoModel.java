package it.reactive.academy.springMvc.model;

import java.util.Set;

public class TorneoModel {
    private Integer idTorneo;
    private String nomeTorneo;
    private Set<SquadraModel> squadre;

    public TorneoModel(Integer idTorneo, String nomeTorneo, Set<SquadraModel> squadre) {
        this.idTorneo = idTorneo;
        this.nomeTorneo = nomeTorneo;
        this.squadre = squadre;
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

    public Set<SquadraModel> getSquadre() {
        return squadre;
    }

    public void setSquadre(Set<SquadraModel> squadre) {
        this.squadre = squadre;
    }
}
