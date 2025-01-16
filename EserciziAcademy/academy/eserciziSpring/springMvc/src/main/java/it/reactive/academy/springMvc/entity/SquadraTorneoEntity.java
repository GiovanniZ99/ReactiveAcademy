package it.reactive.academy.springMvc.entity;

public class SquadraTorneoEntity {

    private SquadraEntity squadraEntity;

    private TorneoEntity torneoEntity;

    public SquadraTorneoEntity(SquadraEntity squadraEntity, TorneoEntity torneoEntity) {
        this.squadraEntity = squadraEntity;
        this.torneoEntity = torneoEntity;
    }

    public SquadraTorneoEntity() {
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
