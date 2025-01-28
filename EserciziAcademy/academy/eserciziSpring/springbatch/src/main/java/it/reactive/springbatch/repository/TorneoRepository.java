package it.reactive.springbatch.repository;

import it.reactive.springbatch.entity.TorneoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TorneoRepository extends JpaRepository<TorneoEntity, Integer> {
}
