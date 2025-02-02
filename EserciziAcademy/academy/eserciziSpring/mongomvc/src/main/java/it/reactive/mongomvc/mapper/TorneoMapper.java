package it.reactive.mongomvc.mapper;

import it.reactive.mongomvc.document.Torneo;
import it.reactive.mongomvc.dto.TorneoDTO;
import it.reactive.mongomvc.resource.TorneoResource;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface TorneoMapper {

    TorneoResource torneoToResource(Torneo torneo);

    Torneo torneoDTOToDocument(TorneoDTO torneoDTO);


}
