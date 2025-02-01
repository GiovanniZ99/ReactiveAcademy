package it.reactive.mongomvc.mapper;

import it.reactive.mongomvc.document.Giocatore;
import it.reactive.mongomvc.dto.GiocatoreDTO;
import it.reactive.mongomvc.resource.GiocatoreResource;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface GiocatoreMapper {
    GiocatoreResource giocatoreToResource(Giocatore giocatore);

    Giocatore giocatoreDTOToDocument(GiocatoreDTO giocatoreDTO);

    }
