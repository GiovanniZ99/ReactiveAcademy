package it.reactive.academy.springMvc.entity;

import javax.persistence.*;

@Entity
@Table(name = "squadra_torneo")
public class SquadraTorneoEntity {

    @EmbeddedId
    private SquadraTorneoId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idSquadra")
    private SquadraEntity squadra;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idTorneo")
    private TorneoEntity torneo;

    public SquadraTorneoEntity(SquadraTorneoId id, SquadraEntity squadra, TorneoEntity torneo) {
        this.id = id;
        this.squadra = squadra;
        this.torneo = torneo;
    }

    public SquadraTorneoEntity() {
    }

    public SquadraTorneoId getId() {
        return id;
    }

    public void setId(SquadraTorneoId id) {
        this.id = id;
    }

    public SquadraEntity getSquadra() {
        return squadra;
    }

    public void setSquadra(SquadraEntity squadra) {
        this.squadra = squadra;
    }

    public TorneoEntity getTorneo() {
        return torneo;
    }

    public void setTorneo(TorneoEntity torneo) {
        this.torneo = torneo;
    }
}
