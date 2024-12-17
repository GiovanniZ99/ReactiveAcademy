package it.reactive.academy.springMvc.repository.dao;

import it.reactive.academy.springMvc.dto.SquadraDTO;
import it.reactive.academy.springMvc.model.SquadraModel;

public interface SquadraDao {
    SquadraModel create(SquadraDTO squadraDTO);
    SquadraModel read(int id);
    SquadraModel update(int id, SquadraDTO squadraDTO);
    SquadraModel delete(int id);
}
