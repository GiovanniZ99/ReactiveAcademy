package it.reactive.mongomvc.repository;

import it.reactive.mongomvc.document.Squadra;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SquadraRepository extends MongoRepository<Squadra, String> {
}
