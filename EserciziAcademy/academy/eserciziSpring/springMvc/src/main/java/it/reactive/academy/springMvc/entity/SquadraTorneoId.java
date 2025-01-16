package it.reactive.academy.springMvc.entity;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import java.io.Serializable;

@Embeddable
public class SquadraTorneoId implements Serializable {

//    @ManyToOne
////    @MapsId("id_squadra")
//    @JoinColumn(name = "id_squadra", referencedColumnName = "id")
    private SquadraEntity squadraEntity;

    private TorneoEntity torneoEntity;
    public SquadraTorneoId(SquadraEntity squadraEntity, TorneoEntity torneoEntity) {
        this.squadraEntity = squadraEntity;
        this.torneoEntity = torneoEntity;
    }

    public SquadraTorneoId() {
    }

    public SquadraEntity getSquadraEntity() {
        return squadraEntity;
    }

    public void setSquadraEntity(SquadraEntity squadraEntity) {
        this.squadraEntity = squadraEntity;
    }

    public TorneoEntity getTorneoEntity() {
        return torneoEntity;
    }

    public void setTorneoEntity(TorneoEntity torneoEntity) {
        this.torneoEntity = torneoEntity;
    }
}
