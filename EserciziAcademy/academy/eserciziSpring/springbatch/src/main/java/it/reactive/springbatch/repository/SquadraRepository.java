package it.reactive.springbatch.repository;

import it.reactive.springbatch.entity.SquadraEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SquadraRepository extends JpaRepository<SquadraEntity, Integer> {
}
