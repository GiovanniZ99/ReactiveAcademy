package it.reactive.academy.springMvc.dto.extended;

public class TifoseriaDTOExtended {

    private Integer idTifoseria;
    private String nomeTifoseria;
    private SquadraDTOExtended squadra;

    public TifoseriaDTOExtended() {
    }

    public TifoseriaDTOExtended(Integer idTifoseria, String nomeTifoseria, SquadraDTOExtended squadra) {
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

    public SquadraDTOExtended getSquadra() {
        return squadra;
    }

    public void setSquadra(SquadraDTOExtended squadra) {
        this.squadra = squadra;
    }
}
