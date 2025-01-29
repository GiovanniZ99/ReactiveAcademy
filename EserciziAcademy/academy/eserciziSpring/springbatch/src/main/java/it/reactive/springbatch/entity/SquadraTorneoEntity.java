package it.reactive.springbatch.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "squadra_torneo")
public class SquadraTorneoEntity {

    @EmbeddedId
    private SquadraTorneoId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idSquadra")
    @JoinColumn(name = "id_squadra")
    private SquadraEntity squadra;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idTorneo")
    @JoinColumn(name = "id_torneo")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SquadraTorneoEntity that = (SquadraTorneoEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(squadra, that.squadra) && Objects.equals(torneo, that.torneo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, squadra, torneo);
    }
}
