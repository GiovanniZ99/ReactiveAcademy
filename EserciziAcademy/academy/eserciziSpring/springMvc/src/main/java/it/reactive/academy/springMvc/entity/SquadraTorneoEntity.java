package it.reactive.academy.springMvc.entity;

import javax.persistence.*;

@Entity
@Table(name = "squadra_torneo")
public class SquadraTorneoEntity {

    @EmbeddedId
    private SquadraTorneoId id;

    public SquadraTorneoEntity(SquadraTorneoId id) {
        this.id = id;
    }

    public SquadraTorneoEntity() {
    }

    public SquadraTorneoId getId() {
        return id;
    }

    public void setId(SquadraTorneoId id) {
        this.id = id;
    }
}
