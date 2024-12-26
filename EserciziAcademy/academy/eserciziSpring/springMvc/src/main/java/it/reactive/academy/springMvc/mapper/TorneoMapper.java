package it.reactive.academy.springMvc.mapper;

import it.reactive.academy.springMvc.dto.TorneoDTO;
import it.reactive.academy.springMvc.dto.extended.TorneoDTOExtended;
import it.reactive.academy.springMvc.model.TorneoModel;
import it.reactive.academy.springMvc.resource.Torneo;

import java.util.stream.Collectors;

public class TorneoMapper {
    public static TorneoDTOExtended torneoModelToDtoExtended(TorneoModel torneoModel) {
        TorneoDTOExtended torneoDTOExtended = new TorneoDTOExtended();
        if(torneoModel.getIdTorneo() != null) {
            torneoDTOExtended.setIdTorneo(torneoModel.getIdTorneo());
        }
        torneoDTOExtended.setNomeTorneo(torneoModel.getNomeTorneo());
        return torneoDTOExtended;
    }
    public static TorneoModel torneoDTOExtendedToModel(TorneoDTOExtended torneoDTOExtended){
        TorneoModel torneoModel = new TorneoModel();
        torneoModel.setIdTorneo(torneoDTOExtended.getIdTorneo());
        torneoModel.setNomeTorneo(torneoDTOExtended.getNomeTorneo());
        return torneoModel;
    }
    public static Torneo torneoDtoExtendedToResource(TorneoDTOExtended torneoDTOExtended){
        Torneo torneo = new Torneo();
        if(torneoDTOExtended.getIdTorneo() != null){
        torneo.setIdTorneo(torneoDTOExtended.getIdTorneo());
        }
        torneo.setNomeTorneo(torneoDTOExtended.getNomeTorneo());
        if(torneoDTOExtended.getSquadre() != null) {
            torneo.setSquadre(torneoDTOExtended.getSquadre()
                    .stream()
                    .map(elem -> SquadraMapper.squadraDtoExtendedToResource(elem))
                    .collect(Collectors.toSet()));
        }
        return torneo;
    }
    public static TorneoDTOExtended torneoDtoToDTOExtended(TorneoDTO torneoDTO){
        TorneoDTOExtended torneoDTOExtended = new TorneoDTOExtended();
        torneoDTOExtended.setNomeTorneo(torneoDTO.getNomeTorneo());
        return torneoDTOExtended;
    }
}
