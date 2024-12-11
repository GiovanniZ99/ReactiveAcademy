package it.reactive.academy.springMvc.resource;

import java.util.Set;

public class Torneo {

    Integer idTorneo;
    String nomeTorneo;
    Set<Squadra> squadre;

    public Torneo(){}

    public Torneo(Integer idTorneo, String nomeTorneo, Set<Squadra> squadre) {
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

    public Set<Squadra> getSquadre() {
        return squadre;
    }

    public void setSquadre(Set<Squadra> squadre) {
        this.squadre = squadre;
    }
}
