package it.reactive.academy.springMvc.mapper;

import it.reactive.academy.springMvc.dto.extended.TrasferimentiDTOExtended;
import it.reactive.academy.springMvc.model.TrasferimentiModel;
import it.reactive.academy.springMvc.resource.Trasferimenti;

public class TrasferimentiMapper {
    public static Trasferimenti trasferimentiDTOExtendedToResource(TrasferimentiDTOExtended trasferimentiDTOExtended) {
        Trasferimenti trasferimenti = new Trasferimenti();
        trasferimenti.setAnno(trasferimenti.getAnno());
        trasferimenti.setNomeSquadraStorica(trasferimentiDTOExtended.getNomeSquadraStorica());
        return trasferimenti;
    }
    public static TrasferimentiModel trasferimentiDtoExtendedToModel(TrasferimentiDTOExtended trasferimentiDTOExtended){
        TrasferimentiModel trasferimentiModel = new TrasferimentiModel();
        trasferimentiModel.setAnno(trasferimentiDTOExtended.getAnno());
        trasferimentiModel.setNomeSquadraStorica(trasferimentiDTOExtended.getNomeSquadraStorica());
        return trasferimentiModel;
    }
}
