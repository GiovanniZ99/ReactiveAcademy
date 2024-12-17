package it.reactive.academy.springMvc.repository.mapper;

import it.reactive.academy.springMvc.dto.SquadraDTO;
import it.reactive.academy.springMvc.model.SquadraModel;
import it.reactive.academy.springMvc.resource.Squadra;

public class SquadraMapper {
    public SquadraDTO squadraModelToDTO(SquadraModel squadraModel) {
        SquadraDTO squadraDTO = new SquadraDTO();
        squadraDTO.setNome(squadraModel.getNome());
        squadraDTO.setColoriSociali(squadraModel.getColoriSociali());
        return squadraDTO;
    }

    public SquadraModel squadraDTOToModel(SquadraDTO squadraDTO) {
        SquadraModel squadraModel = new SquadraModel();
        squadraModel.setNome(squadraDTO.getNome());
        squadraModel.setColoriSociali(squadraDTO.getColoriSociali());
        return squadraModel;
    }

    public Squadra squadraDTOToResource(SquadraDTO squadraDTO) {
        Squadra squadra = new Squadra();
        squadra.setNome(squadraDTO.getNome());
        squadra.setColoriSociali(squadraDTO.getColoriSociali());
        return squadra;
    }
}
