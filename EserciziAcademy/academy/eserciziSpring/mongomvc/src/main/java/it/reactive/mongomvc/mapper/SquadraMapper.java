package it.reactive.mongomvc.mapper;


import it.reactive.mongomvc.document.Squadra;
import it.reactive.mongomvc.dto.SquadraDTO;
import it.reactive.mongomvc.resource.SquadraResource;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface SquadraMapper {
    Squadra squadraDTOToDocument(SquadraDTO squadraDTO);

    SquadraResource squadraDocumentToResource(Squadra squadra);

}
