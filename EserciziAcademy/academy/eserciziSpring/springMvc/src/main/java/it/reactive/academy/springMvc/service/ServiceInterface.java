package it.reactive.academy.springMvc.service;

public interface ServiceInterface<I, O> {
    O create(I input);

    O read(int id);

    O update(int id, I input);

    O delete(int id);
}
