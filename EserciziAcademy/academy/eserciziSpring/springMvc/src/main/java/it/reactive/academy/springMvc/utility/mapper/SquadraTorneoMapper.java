package it.reactive.academy.springMvc.utility.mapper;

import it.reactive.academy.springMvc.dto.extended.SquadraTorneoDTOExtended;
import it.reactive.academy.springMvc.entity.SquadraTorneoEntity;
import it.reactive.academy.springMvc.entity.SquadraTorneoId;

public class SquadraTorneoMapper {

    private SquadraTorneoMapper() {
    }

    public static SquadraTorneoId squadraTorneoDtoExtendedToModel(SquadraTorneoDTOExtended squadraTorneoDTOExtended) {
        SquadraTorneoId squadraTorneoId = new SquadraTorneoId();
        squadraTorneoId.setIdSquadra(squadraTorneoDTOExtended.getSquadraDTOExtended().getIdSquadra());
        squadraTorneoId.setIdTorneo(squadraTorneoDTOExtended.getTorneoDTOExtended().getIdTorneo());
        return squadraTorneoId;
    }

    public static SquadraTorneoDTOExtended squadraTorneoEntityToDtoExtended(SquadraTorneoEntity squadraTorneoEntity) {
        SquadraTorneoDTOExtended squadraTorneoDTOExtended = new SquadraTorneoDTOExtended();
        squadraTorneoDTOExtended.setSquadraDTOExtended(SquadraMapper.squadraEntityToDtoExtendended(squadraTorneoEntity.getSquadra()));
        squadraTorneoDTOExtended.setTorneoDTOExtended(TorneoMapper.torneoEntityToDtoExtended(squadraTorneoEntity.getTorneo()));
        return squadraTorneoDTOExtended;
    }
}
