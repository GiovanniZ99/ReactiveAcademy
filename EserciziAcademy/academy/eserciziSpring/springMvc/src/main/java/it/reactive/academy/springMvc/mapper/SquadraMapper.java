package it.reactive.academy.springMvc.mapper;

import it.reactive.academy.springMvc.dto.SquadraDTO;
import it.reactive.academy.springMvc.dto.extended.SquadraDTOExtended;
import it.reactive.academy.springMvc.model.SquadraModel;
import it.reactive.academy.springMvc.resource.Squadra;

import java.util.stream.Collectors;

public class SquadraMapper {
    public static SquadraDTOExtended squadraModelToDtoExtendended(SquadraModel squadraModel) {
        SquadraDTOExtended squadraDTOExtended = new SquadraDTOExtended();
        if(squadraModel.getIdSquadra() != null){
        squadraDTOExtended.setIdSquadra(squadraModel.getIdSquadra());
        }
        if(squadraModel.getNome()!= null){
        squadraDTOExtended.setNome(squadraModel.getNome());
        }
        if(squadraModel.getColoriSociali() != null){
        squadraDTOExtended.setColoriSociali(squadraModel.getColoriSociali());
        }
        return squadraDTOExtended;
    }

    public static SquadraModel squadraDtoExtendedToModel(SquadraDTOExtended squadraDTOExtended) {
        SquadraModel squadraModel = new SquadraModel();
        if(squadraDTOExtended.getIdSquadra()!= null){
            squadraModel.setIdSquadra(squadraDTOExtended.getIdSquadra());
        }
        if(squadraDTOExtended.getNome() != null){
        squadraModel.setNome(squadraDTOExtended.getNome());}
        if(squadraDTOExtended.getColoriSociali() != null){
        squadraModel.setColoriSociali(squadraDTOExtended.getColoriSociali());
        }
        return squadraModel;
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
