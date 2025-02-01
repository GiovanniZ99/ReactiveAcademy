package it.reactive.mongomvc.repository;

import it.reactive.mongomvc.document.Torneo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TorneoRepository extends MongoRepository<Torneo, String> {
}
