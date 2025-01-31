package it.reactive.springbatch.repository;

import it.reactive.springbatch.entity.SquadraEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SquadraRepository extends JpaRepository<SquadraEntity, Integer> {
    Optional<SquadraEntity> findByNome(String nome);
}
