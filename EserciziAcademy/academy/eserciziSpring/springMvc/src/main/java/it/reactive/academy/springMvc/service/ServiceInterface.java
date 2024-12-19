package it.reactive.academy.springMvc.service;

import java.util.List;

public interface ServiceInterface<I, O> {
    O create(I input);

    List<O> read(Boolean input);

    O update(int id, I input);

    O delete(int id);
}
