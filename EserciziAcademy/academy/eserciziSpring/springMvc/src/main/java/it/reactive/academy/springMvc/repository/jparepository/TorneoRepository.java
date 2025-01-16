package it.reactive.academy.springMvc.repository.jparepository;

import it.reactive.academy.springMvc.entity.TorneoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TorneoRepository extends JpaRepository<TorneoEntity, Integer> {
}
