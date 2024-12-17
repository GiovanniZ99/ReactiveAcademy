package it.reactive.academy.springMvc.repository.mapper;

import it.reactive.academy.springMvc.dto.GiocatoreDTO;
import it.reactive.academy.springMvc.model.GiocatoreModel;
import org.springframework.beans.factory.annotation.Autowired;

public class GiocatoreMapper {
    @Autowired
    GiocatoreDTO giocatoreDTO;
    @Autowired
    GiocatoreModel giocatoreModel;
    public GiocatoreDTO giocatoreModelToDto(GiocatoreModel giocatoreModel){
        giocatoreDTO.setNomeCognome(giocatoreModel.getNomeCognome());
        return giocatoreDTO;
    }
    public GiocatoreModel giocatoreDtoToModel(GiocatoreDTO giocatoreDTO){
        giocatoreModel.setNomeCognome(giocatoreDTO.getNomeCognome());
        return giocatoreModel;
    }

}
