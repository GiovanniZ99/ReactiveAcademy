package it.reactive.academy.springMvc.repository.jparepository;

import it.reactive.academy.springMvc.entity.SquadraTorneoId;
import it.reactive.academy.springMvc.entity.SquadraTorneoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SquadraTorneoRepository extends JpaRepository<SquadraTorneoEntity, SquadraTorneoId> {

}
