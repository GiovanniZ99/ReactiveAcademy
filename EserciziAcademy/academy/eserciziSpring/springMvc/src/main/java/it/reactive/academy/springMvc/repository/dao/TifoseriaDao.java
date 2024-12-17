package it.reactive.academy.springMvc.repository.dao;

import it.reactive.academy.springMvc.dto.TifoseriaDTO;
import it.reactive.academy.springMvc.model.TifoseriaModel;

public interface TifoseriaDao {
    TifoseriaModel create(TifoseriaDTO tifoseriaDTO);
    TifoseriaModel read(int id);
    TifoseriaModel update(int id, TifoseriaDTO tifoseriaDTO);
    TifoseriaModel delete(int id);
}
