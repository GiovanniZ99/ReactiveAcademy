package it.reactive.academy.springMvc.dto.extended;

public class SquadraTorneoDTOExtended {
    private Integer idSquadra;
    private Integer idTorneo;

    public SquadraTorneoDTOExtended(Integer idSquadra, Integer idTorneo) {
        this.idSquadra = idSquadra;
        this.idTorneo = idTorneo;
    }

    public SquadraTorneoDTOExtended() {
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
