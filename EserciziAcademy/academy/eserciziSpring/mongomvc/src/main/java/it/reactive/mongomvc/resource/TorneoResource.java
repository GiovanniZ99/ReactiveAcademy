package it.reactive.mongomvc.resource;

import java.util.Set;

public class TorneoResource {

 private Integer idTorneo;
 private String nomeTorneo;
 private Set<SquadraResource> squadre;

    public TorneoResource(){}

    public TorneoResource(Integer idTorneo, String nomeTorneo, Set<SquadraResource> squadre) {
       super();
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

    public Set<SquadraResource> getSquadre() {
        return squadre;
    }

    public void setSquadre(Set<SquadraResource> squadre) {
        this.squadre = squadre;
    }
}
