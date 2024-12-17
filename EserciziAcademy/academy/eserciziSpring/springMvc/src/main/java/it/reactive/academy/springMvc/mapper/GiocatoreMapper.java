package it.reactive.academy.springMvc.mapper;

import it.reactive.academy.springMvc.dto.GiocatoreDTO;
import it.reactive.academy.springMvc.dto.extended.GiocatoreDTOExtended;
import it.reactive.academy.springMvc.dto.extended.TrasferimentiDTOExtended;
import it.reactive.academy.springMvc.model.GiocatoreModel;
import it.reactive.academy.springMvc.resource.Giocatore;
import it.reactive.academy.springMvc.resource.Trasferimenti;

import java.util.stream.Collectors;

public class GiocatoreMapper {
    // DTO-IN -> DTO-EXTENDED  S//    DTO-EXTEDNED -> MODEL S//    MODEL -> DTO-EXTENDED S//    DTO-EXTENDED -> RESOURCE
    public static GiocatoreDTOExtended giocatoreModelToDTOExtended(GiocatoreModel giocatoreModel) {
        GiocatoreDTOExtended giocatoreDTOExtended = new GiocatoreDTOExtended();
        giocatoreDTOExtended.setNomeCognome(giocatoreModel.getNomeCognome());
        giocatoreDTOExtended.setIdGiocatore(giocatoreModel.getIdGiocatore());
        giocatoreDTOExtended.setSquadra(SquadraMapper.squadraModelToDtoExtendended(giocatoreModel.getSquadra()));
        return giocatoreDTOExtended;
    }

    public static GiocatoreDTOExtended giocatoreDTOToDtoExtended(GiocatoreDTO giocatoreDTO) {
        GiocatoreDTOExtended giocatoreDTOExtended = new GiocatoreDTOExtended();
        giocatoreDTOExtended.setNomeCognome(giocatoreDTO.getNomeCognome());
        return giocatoreDTOExtended;
    }

    public static GiocatoreModel giocatoreDtoExtendedToModel(GiocatoreDTOExtended giocatoreDTOExtended) {
        GiocatoreModel giocatoreModel = new GiocatoreModel();
        giocatoreModel.setNomeCognome(giocatoreDTOExtended.getNomeCognome());
        giocatoreModel.setNumeroAmmonizioni(giocatoreModel.getNumeroAmmonizioni());
        giocatoreModel.setSquadra(SquadraMapper.squadraDtoExtendedToModel(giocatoreDTOExtended.getSquadra()));
        giocatoreModel.setTrasferimenti(giocatoreDTOExtended.getTrasferimenti()
                .stream()
                .map(elem -> TrasferimentiMapper.trasferimentiDtoExtendedToModel(elem))
                .collect(Collectors.toSet()));
        return giocatoreModel;
    }

    public static Giocatore giocatoreDTOExtendedToResource(GiocatoreDTOExtended giocatoreDTOExtended) {
        Giocatore giocatore = new Giocatore();
        giocatore.setIdGiocatore(giocatoreDTOExtended.getIdGiocatore());
        giocatore.setNomeCognome(giocatoreDTOExtended.getNomeCognome());
        giocatore.setNumeroAmmonizioni(giocatoreDTOExtended.getNumeroAmmonizioni());

        giocatore.setTrasferimenti(giocatoreDTOExtended.getTrasferimenti().stream()
                .map(trasferimento -> TrasferimentiMapper.trasferimentiDTOExtendedToResource(trasferimento))
                .collect(Collectors.toSet()));
        return giocatore;
    }

    public static Trasferimenti trasferimentiDTOExtendedToResource(TrasferimentiDTOExtended trasferimentiDTOExtended) {
        Trasferimenti trasferimenti = new Trasferimenti();
        trasferimenti.setAnno(trasferimentiDTOExtended.getAnno());
        trasferimenti.setNomeSquadraStorica(trasferimentiDTOExtended.getNomeSquadraStorica());
        return trasferimenti;
    }
}
