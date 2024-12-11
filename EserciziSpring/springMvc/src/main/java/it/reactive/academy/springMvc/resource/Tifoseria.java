package it.reactive.academy.SpringMvcStep1.resource;

public class Tifoseria {

    Integer idTifoseria;
    String nomeTifoseria;
    Squadra squadra;

    public Tifoseria(){}

    public Tifoseria(Integer idTifoseria, String nomeTifoseria, Squadra squadra) {
        super();
        this.idTifoseria = idTifoseria;
        this.nomeTifoseria = nomeTifoseria;
        this.squadra = squadra;
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
