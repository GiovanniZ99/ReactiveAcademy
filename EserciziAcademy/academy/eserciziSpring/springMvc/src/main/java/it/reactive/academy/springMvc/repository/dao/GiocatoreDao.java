package it.reactive.academy.springMvc.repository.dao;

import it.reactive.academy.springMvc.dto.GiocatoreDTO;
import it.reactive.academy.springMvc.model.GiocatoreModel;

public interface GiocatoreDao {
    GiocatoreModel create(GiocatoreDTO giocatoreDTO);
    GiocatoreModel read(int id);
    GiocatoreModel update(int id, GiocatoreDTO giocatoreDTO);
    GiocatoreModel delete(int id);
}
