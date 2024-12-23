package it.reactive.academy.springMvc.mapper;

import it.reactive.academy.springMvc.dto.SquadraDiGiocatoriDTO;
import it.reactive.academy.springMvc.dto.extended.SquadraDTOExtended;
import it.reactive.academy.springMvc.dto.extended.SquadraDiGiocatoriDTOExtended;

import java.util.stream.Collectors;

public class SquadraDiGiocatoriMapper {
    public static SquadraDTOExtended squadraDiGiocatoriToDTOExtended(SquadraDiGiocatoriDTO squadraDiGiocatoriDTO){
        SquadraDTOExtended squadraDTOExtended = new SquadraDTOExtended();
        squadraDTOExtended.setNome(squadraDiGiocatoriDTO.getNome());
        squadraDTOExtended.setColoriSociali(squadraDiGiocatoriDTO.getColoriSociali());
        squadraDTOExtended.setGiocatori(squadraDiGiocatoriDTO.getListaGiocatori().stream()
                .map(GiocatoreMapper::giocatoreDTOToDtoExtended)
                .collect(Collectors.toSet()));
        return squadraDTOExtended;
    }
}
