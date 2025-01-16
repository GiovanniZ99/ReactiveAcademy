package it.reactive.academy.springMvc.utility.mapper;

import it.reactive.academy.springMvc.dto.extended.SquadraTorneoDTOExtended;
import it.reactive.academy.springMvc.entity.SquadraTorneoEntity;

public class SquadraTorneoMapper {

    private SquadraTorneoMapper() {
    }

    public static SquadraTorneoEntity squadraTorneoDtoExtendedToModel(SquadraTorneoDTOExtended squadraTorneoDTOExtended) {
        SquadraTorneoEntity squadraTorneoEntity = new SquadraTorneoEntity();
        squadraTorneoEntity.setIdSquadra(squadraTorneoDTOExtended.getIdSquadra());
        squadraTorneoEntity.setIdTorneo(squadraTorneoDTOExtended.getIdTorneo());
        return squadraTorneoEntity;
    }

    public static SquadraTorneoDTOExtended squadraEntityToDtoExtended(SquadraTorneoEntity squadraTorneoEntity) {
        SquadraTorneoDTOExtended squadraTorneoDTOExtended = new SquadraTorneoDTOExtended();
        squadraTorneoDTOExtended.setIdSquadra(squadraTorneoEntity.getIdSquadra());
        squadraTorneoDTOExtended.setIdTorneo(squadraTorneoEntity.getIdTorneo());
        return squadraTorneoDTOExtended;
    }
}
