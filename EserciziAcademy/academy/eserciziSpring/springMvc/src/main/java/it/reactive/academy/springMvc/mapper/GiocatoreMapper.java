package it.reactive.academy.springMvc.mapper;

import it.reactive.academy.springMvc.dto.GiocatoreDTO;
import it.reactive.academy.springMvc.dto.extended.GiocatoreDTOExtended;
import it.reactive.academy.springMvc.model.GiocatoreModel;
import it.reactive.academy.springMvc.resource.Giocatore;

import java.util.stream.Collectors;

public class GiocatoreMapper {
    // DTO-IN -> DTO-EXTENDED s  //    DTO-EXTEDNED -> MODEL s //    MODEL -> DTO-EXTENDED s //    DTO-EXTENDED -> RESOURCE s
    public static GiocatoreDTOExtended giocatoreModelToDTOExtended(GiocatoreModel giocatoreModel) {
        GiocatoreDTOExtended giocatoreDTOExtended = new GiocatoreDTOExtended();
        giocatoreDTOExtended.setIdGiocatore(giocatoreModel.getIdGiocatore());
        giocatoreDTOExtended.setNomeCognome(giocatoreModel.getNomeCognome());
        giocatoreDTOExtended.setNumeroAmmonizioni(giocatoreModel.getNumeroAmmonizioni());
        if(giocatoreModel.getSquadra() != null) {
            giocatoreDTOExtended.setSquadra(SquadraMapper.squadraModelToDtoExtendended(giocatoreModel.getSquadra()));
        }
        return giocatoreDTOExtended;
    }

    public static GiocatoreDTOExtended giocatoreDTOToDtoExtended(GiocatoreDTO giocatoreDTO) {
        GiocatoreDTOExtended giocatoreDTOExtended = new GiocatoreDTOExtended();
        giocatoreDTOExtended.setNomeCognome(giocatoreDTO.getNomeCognome());
        return giocatoreDTOExtended;
    }

    public static GiocatoreModel giocatoreDtoExtendedToModel(GiocatoreDTOExtended giocatoreDTOExtended) {
        GiocatoreModel giocatoreModel = new GiocatoreModel();
        if(giocatoreDTOExtended.getNomeCognome()!= null) {
            giocatoreModel.setNomeCognome(giocatoreDTOExtended.getNomeCognome());
        }
        if(giocatoreDTOExtended.getNumeroAmmonizioni() != null) {
            giocatoreModel.setNumeroAmmonizioni(giocatoreModel.getNumeroAmmonizioni());
        }
        if(giocatoreDTOExtended.getSquadra()!= null) {
            giocatoreModel.setSquadra(SquadraMapper.squadraDtoExtendedToModel(giocatoreDTOExtended.getSquadra()));
        }
        if(giocatoreDTOExtended.getTrasferimenti()!= null){
        giocatoreModel.setTrasferimenti(giocatoreDTOExtended.getTrasferimenti()
                .stream()
                .map(elem -> TrasferimentiMapper.trasferimentiDtoExtendedToModel(elem))
                .collect(Collectors.toSet()));}
        return giocatoreModel;
    }

    public static Giocatore giocatoreDTOExtendedToResource(GiocatoreDTOExtended giocatoreDTOExtended) {
        Giocatore giocatore = new Giocatore();
        giocatore.setIdGiocatore(giocatoreDTOExtended.getIdGiocatore());
        giocatore.setNomeCognome(giocatoreDTOExtended.getNomeCognome());
        giocatore.setNumeroAmmonizioni(giocatoreDTOExtended.getNumeroAmmonizioni());
        if(giocatoreDTOExtended.getTrasferimenti()!=null) {
            giocatore.setTrasferimenti(giocatoreDTOExtended.getTrasferimenti().stream()
                    .map(trasferimento -> TrasferimentiMapper.trasferimentiDTOExtendedToResource(trasferimento))
                    .collect(Collectors.toSet()));
        }
        return giocatore;
    }
}
