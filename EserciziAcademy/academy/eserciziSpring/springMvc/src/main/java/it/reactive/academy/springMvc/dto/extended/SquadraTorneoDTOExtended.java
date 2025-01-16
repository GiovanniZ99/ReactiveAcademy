package it.reactive.academy.springMvc.dto.extended;

public class SquadraTorneoDTOExtended {
    private SquadraDTOExtended squadraDTOExtended;
    private TorneoDTOExtended torneoDTOExtended;

    public SquadraTorneoDTOExtended(SquadraDTOExtended squadraDTOExtended, TorneoDTOExtended torneoDTOExtended) {
        this.squadraDTOExtended = squadraDTOExtended;
        this.torneoDTOExtended = torneoDTOExtended;
    }

    public SquadraTorneoDTOExtended() {
    }

    public SquadraDTOExtended getSquadraDTOExtended() {
        return squadraDTOExtended;
    }

    public void setSquadraDTOExtended(SquadraDTOExtended squadraDTOExtended) {
        this.squadraDTOExtended = squadraDTOExtended;
    }

    public TorneoDTOExtended getTorneoDTOExtended() {
        return torneoDTOExtended;
    }

    public void setTorneoDTOExtended(TorneoDTOExtended torneoDTOExtended) {
        this.torneoDTOExtended = torneoDTOExtended;
    }
}
