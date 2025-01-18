package it.reactive.academy.springMvc.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SquadraTorneoId implements Serializable {

    @Column(name = "id_squadra")
    private Integer idSquadra;

    @Column(name = "id_torneo")
    private Integer idTorneo;

    public SquadraTorneoId(Integer idSquadra, Integer idTorneo) {
        this.idSquadra = idSquadra;
        this.idTorneo = idTorneo;
    }

    public SquadraTorneoId() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SquadraTorneoId that = (SquadraTorneoId) o;
        return Objects.equals(idSquadra, that.idSquadra) && Objects.equals(idTorneo, that.idTorneo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSquadra, idTorneo);
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
