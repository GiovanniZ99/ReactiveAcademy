package it.reactive.academy.springMvc.entity;

import javax.persistence.*;

@Entity
@Table(name = "squadra_torneo")
public class SquadraTorneoJpa {

    @EmbeddedId
    private SquadraTorneoEntity id;

    public SquadraTorneoJpa(SquadraTorneoEntity id) {
        this.id = id;
    }

    public SquadraTorneoJpa() {
    }

    public SquadraTorneoEntity getId() {
        return id;
    }

    public void setId(SquadraTorneoEntity id) {
        this.id = id;
    }
}
