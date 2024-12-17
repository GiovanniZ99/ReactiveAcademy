package it.reactive.academy.springMvc.mapper;

import it.reactive.academy.springMvc.dto.extended.TorneoDTOExtended;
import it.reactive.academy.springMvc.model.TorneoModel;

import java.util.stream.Collectors;

public class TorneoMapper {
    public static TorneoDTOExtended torneoModelToDtoExtended(TorneoModel torneoModel) {
        TorneoDTOExtended torneoDTOExtended = new TorneoDTOExtended();
        torneoDTOExtended.setIdTorneo(torneoModel.getIdTorneo());
        torneoDTOExtended.setNomeTorneo(torneoModel.getNomeTorneo());
        torneoDTOExtended.setSquadre(torneoModel.getSquadre()
                .stream()
                .map(elem -> SquadraMapper.squadraModelToDtoExtendended(elem))
                .collect(Collectors.toSet()));
        return torneoDTOExtended;
    }
}
