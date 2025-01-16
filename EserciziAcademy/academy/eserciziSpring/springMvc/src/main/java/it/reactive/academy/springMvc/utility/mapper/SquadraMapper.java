package it.reactive.academy.springMvc.utility.mapper;

import it.reactive.academy.springMvc.dto.SquadraDTO;
import it.reactive.academy.springMvc.dto.extended.SquadraDTOExtended;
import it.reactive.academy.springMvc.entity.SquadraEntity;
import it.reactive.academy.springMvc.resource.Squadra;

import java.util.stream.Collectors;

public class SquadraMapper {
    private SquadraMapper() {
    }

    public static SquadraDTOExtended squadraEntityToDtoExtendended(SquadraEntity squadraEntity) {
        SquadraDTOExtended squadraDTOExtended = new SquadraDTOExtended();
        if(squadraEntity.getIdSquadra() != null){
        squadraDTOExtended.setIdSquadra(squadraEntity.getIdSquadra());
        }
        if(squadraEntity.getNome()!= null){
        squadraDTOExtended.setNome(squadraEntity.getNome());
        }
        if(squadraEntity.getColoriSociali() != null){
        squadraDTOExtended.setColoriSociali(squadraEntity.getColoriSociali());
        }
        return squadraDTOExtended;
    }

    public static SquadraEntity squadraDtoExtendedToEntity(SquadraDTOExtended squadraDTOExtended) {
        SquadraEntity squadraEntity = new SquadraEntity();
        if(squadraDTOExtended.getIdSquadra()!= null){
            squadraEntity.setIdSquadra(squadraDTOExtended.getIdSquadra());
        }
        if(squadraDTOExtended.getNome() != null){
        squadraEntity.setNome(squadraDTOExtended.getNome());}
        if(squadraDTOExtended.getColoriSociali() != null){
        squadraEntity.setColoriSociali(squadraDTOExtended.getColoriSociali());
        }
        return squadraEntity;
    }

    public static Squadra squadraDtoExtendedToResource(SquadraDTOExtended squadraDTOExtended) {
        Squadra squadra = new Squadra();
            squadra.setIdSquadra(squadraDTOExtended.getIdSquadra());
            squadra.setNome(squadraDTOExtended.getNome());
            squadra.setColoriSociali(squadraDTOExtended.getColoriSociali());
        if (squadraDTOExtended.getTifoseria() != null) {
            squadra.setTifoseria(TifoseriaMapper.tifoseriaDtoExtendedToResource(squadraDTOExtended.getTifoseria()));
        }
        if(squadraDTOExtended.getGiocatori() != null) {
            squadra.setGiocatori(squadraDTOExtended.getGiocatori()
                    .stream()
                    .map(elem -> GiocatoreMapper.giocatoreDTOExtendedToResource(elem))
                    .collect(Collectors.toSet()));
        }
        return squadra;
    }

    public static SquadraDTOExtended squadraDtoToDtoExtended(SquadraDTO squadraDto) {
        SquadraDTOExtended squadraDTOExtended = new SquadraDTOExtended();
        squadraDTOExtended.setNome(squadraDto.getNome());
        squadraDTOExtended.setColoriSociali(squadraDto.getColoriSociali());
        return squadraDTOExtended;
    }
}
