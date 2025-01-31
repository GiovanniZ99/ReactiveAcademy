package it.reactive.academy.springMvc.utility.mapper;

import it.reactive.academy.springMvc.dto.GiocatoreDTO;
import it.reactive.academy.springMvc.dto.extended.GiocatoreDTOExtended;
import it.reactive.academy.springMvc.entity.GiocatoreEntity;
import it.reactive.academy.springMvc.resource.Giocatore;

import java.util.stream.Collectors;

public class GiocatoreMapper {

    private GiocatoreMapper() {
    }

    public static GiocatoreDTOExtended giocatoreEntityToDTOExtended(GiocatoreEntity giocatoreEntity) {
        GiocatoreDTOExtended giocatoreDTOExtended = new GiocatoreDTOExtended();
        giocatoreDTOExtended.setIdGiocatore(giocatoreEntity.getIdGiocatore());
        giocatoreDTOExtended.setNomeCognome(giocatoreEntity.getNomeCognome());
        giocatoreDTOExtended.setNumeroAmmonizioni(giocatoreEntity.getNumeroAmmonizioni());
        if(giocatoreEntity.getSquadra() != null) {
            giocatoreDTOExtended.setSquadra(SquadraMapper.squadraEntityToDtoExtendended(giocatoreEntity.getSquadra()));
        }
        return giocatoreDTOExtended;
    }

    public static GiocatoreDTOExtended giocatoreDTOToDtoExtended(GiocatoreDTO giocatoreDTO) {
        GiocatoreDTOExtended giocatoreDTOExtended = new GiocatoreDTOExtended();
        giocatoreDTOExtended.setNomeCognome(giocatoreDTO.getNomeCognome());
        return giocatoreDTOExtended;
    }

    public static GiocatoreEntity giocatoreDtoExtendedToEntity(GiocatoreDTOExtended giocatoreDTOExtended) {
        GiocatoreEntity giocatoreEntity = new GiocatoreEntity();
        if(giocatoreDTOExtended.getNomeCognome()!= null) {
            giocatoreEntity.setNomeCognome(giocatoreDTOExtended.getNomeCognome());
        }
        if(giocatoreDTOExtended.getNumeroAmmonizioni() != null) {
            giocatoreEntity.setNumeroAmmonizioni(giocatoreEntity.getNumeroAmmonizioni());
        }
        if(giocatoreDTOExtended.getSquadra()!= null) {
            giocatoreEntity.setSquadra(SquadraMapper.squadraDtoExtendedToEntity(giocatoreDTOExtended.getSquadra()));
        }
        if(giocatoreDTOExtended.getTrasferimenti()!= null){
        giocatoreEntity.setTrasferimenti(giocatoreDTOExtended.getTrasferimenti()
                .stream()
                .map(elem -> TrasferimentiMapper.trasferimentiDtoExtendedToModel(elem))
                .collect(Collectors.toSet()));}
        return giocatoreEntity;
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
