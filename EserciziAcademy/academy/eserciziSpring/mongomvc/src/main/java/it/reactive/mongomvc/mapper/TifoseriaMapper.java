package it.reactive.mongomvc.mapper;

import it.reactive.mongomvc.document.Tifoseria;
import it.reactive.mongomvc.dto.TifoseriaDTO;
import it.reactive.mongomvc.resource.TifoseriaResource;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface TifoseriaMapper {

//    TifoseriaResource tifoseriaDtoExtendedToResource(TifoseriaDTOExtended tifoseriaDTOExtended);
//
//    TifoseriaDTOExtended tifoseriaDTOToDTOExtended(TifoseriaDTO tifoseriaDTO);
//
//    TifoseriaDTOExtended tifoseriaToDtoExtended(Tifoseria tifoseria);
//
//    Tifoseria tifoseriaDtoExtendedToEntity(TifoseriaDTOExtended tifoseriaDTOExtended);

}
