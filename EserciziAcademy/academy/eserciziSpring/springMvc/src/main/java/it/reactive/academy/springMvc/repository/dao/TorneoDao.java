package it.reactive.academy.springMvc.repository.dao;

import it.reactive.academy.springMvc.dto.TorneoDTO;
import it.reactive.academy.springMvc.model.TorneoModel;

public interface TorneoDao {
    TorneoModel create(TorneoDTO torneoDTO);
    TorneoModel read(int id);
    TorneoModel update(int id, TorneoDTO torneoDTO);
    TorneoModel delete(int id);
}
