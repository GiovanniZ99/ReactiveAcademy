package it.reactive.academy.springMvc.repository.jparepository;

import it.reactive.academy.springMvc.entity.SquadraEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SquadraRepository extends JpaRepository<SquadraEntity, Integer> {
        Optional<SquadraEntity> findByNome(String nomeSquadra);
}
