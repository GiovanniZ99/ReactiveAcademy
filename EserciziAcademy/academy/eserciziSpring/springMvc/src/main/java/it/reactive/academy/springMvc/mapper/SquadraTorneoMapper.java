package it.reactive.academy.springMvc.mapper;

import it.reactive.academy.springMvc.dto.extended.SquadraTorneoDTOExtended;
import it.reactive.academy.springMvc.model.SquadraTorneoModel;

public class SquadraTorneoMapper {
    public static SquadraTorneoModel squadraTorneoDtoExtendedToModel(SquadraTorneoDTOExtended squadraTorneoDTOExtended) {
        SquadraTorneoModel squadraTorneoModel = new SquadraTorneoModel();
        squadraTorneoModel.setIdSquadra(squadraTorneoDTOExtended.getIdSquadra());
        squadraTorneoModel.setIdTorneo(squadraTorneoDTOExtended.getIdTorneo());
        return squadraTorneoModel;
    }

    public static SquadraTorneoDTOExtended squadraModelToDtoExtended(SquadraTorneoModel squadraTorneoModel) {
        SquadraTorneoDTOExtended squadraTorneoDTOExtended = new SquadraTorneoDTOExtended();
        squadraTorneoDTOExtended.setIdSquadra(squadraTorneoModel.getIdSquadra());
        squadraTorneoDTOExtended.setIdTorneo(squadraTorneoModel.getIdTorneo());
        return squadraTorneoDTOExtended;
    }
}
