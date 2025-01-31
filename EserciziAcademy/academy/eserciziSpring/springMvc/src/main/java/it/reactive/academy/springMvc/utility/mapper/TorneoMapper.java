package it.reactive.academy.springMvc.utility.mapper;

import it.reactive.academy.springMvc.dto.TorneoDTO;
import it.reactive.academy.springMvc.dto.extended.TorneoDTOExtended;
import it.reactive.academy.springMvc.entity.TorneoEntity;
import it.reactive.academy.springMvc.resource.Torneo;

import java.util.stream.Collectors;

public class TorneoMapper {

    private TorneoMapper() {
    }

    public static TorneoDTOExtended torneoEntityToDtoExtended(TorneoEntity torneoEntity) {
        TorneoDTOExtended torneoDTOExtended = new TorneoDTOExtended();
        if(torneoEntity.getIdTorneo() != null) {
            torneoDTOExtended.setIdTorneo(torneoEntity.getIdTorneo());
        }
        if(torneoEntity.getNomeTorneo()!=null) {
            torneoDTOExtended.setNomeTorneo(torneoEntity.getNomeTorneo());
        }
        return torneoDTOExtended;
    }
    public static TorneoEntity torneoDTOExtendedToEntity(TorneoDTOExtended torneoDTOExtended){
        TorneoEntity torneoEntity = new TorneoEntity();
        torneoEntity.setIdTorneo(torneoDTOExtended.getIdTorneo());
        torneoEntity.setNomeTorneo(torneoDTOExtended.getNomeTorneo());
        return torneoEntity;
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
