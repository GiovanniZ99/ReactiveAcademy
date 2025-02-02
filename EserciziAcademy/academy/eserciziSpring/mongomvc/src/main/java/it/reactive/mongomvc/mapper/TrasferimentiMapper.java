package it.reactive.mongomvc.mapper;

import it.reactive.mongomvc.dto.TrasferimentiDTO;
import it.reactive.mongomvc.resource.TrasferimentiResource;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TrasferimentiMapper {
    TrasferimentiResource trasferimentiDTOToResource(TrasferimentiDTO trasferimentiDTO);
}
