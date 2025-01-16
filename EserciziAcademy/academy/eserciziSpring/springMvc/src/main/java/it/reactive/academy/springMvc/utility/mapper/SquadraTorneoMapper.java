package it.reactive.academy.springMvc.utility.mapper;

import it.reactive.academy.springMvc.dto.extended.SquadraTorneoDTOExtended;
import it.reactive.academy.springMvc.entity.SquadraTorneoId;

public class SquadraTorneoMapper {

    private SquadraTorneoMapper() {
    }

    public static SquadraTorneoId squadraTorneoDtoExtendedToModel(SquadraTorneoDTOExtended squadraTorneoDTOExtended) {
        SquadraTorneoId squadraTorneoId = new SquadraTorneoId();
        squadraTorneoId.setSquadraEntity(SquadraMapper.squadraDtoExtendedToEntity(squadraTorneoDTOExtended.getSquadraDTOExtended()));
        squadraTorneoId.setTorneoEntity(TorneoMapper.torneoDTOExtendedToEntity(squadraTorneoDTOExtended.getTorneoDTOExtended()));
        return squadraTorneoId;
    }

    public static SquadraTorneoDTOExtended squadraEntityToDtoExtended(SquadraTorneoId squadraTorneoId) {
        SquadraTorneoDTOExtended squadraTorneoDTOExtended = new SquadraTorneoDTOExtended();
        squadraTorneoDTOExtended.setSquadraDTOExtended(SquadraMapper.squadraEntityToDtoExtendended(squadraTorneoId.getSquadraEntity()));
        squadraTorneoDTOExtended.setTorneoDTOExtended(TorneoMapper.torneoEntityToDtoExtended(squadraTorneoId.getTorneoEntity()));
        return squadraTorneoDTOExtended;
    }
}
