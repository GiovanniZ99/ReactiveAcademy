package it.reactive.academy.springMvc.dto.extended;


import java.util.Set;

public class TorneoDTOExtended {

    private Integer idTorneo;
    private String nomeTorneo;
    private Set<SquadraDTOExtended> squadre;

    public TorneoDTOExtended(){}

    public TorneoDTOExtended(Integer idTorneo, String nomeTorneo,
                             Set<SquadraDTOExtended> squadre) {
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

    public Set<SquadraDTOExtended> getSquadre() {
        return squadre;
    }

    public void setSquadre(Set<SquadraDTOExtended> squadre) {
        this.squadre = squadre;
    }
}
