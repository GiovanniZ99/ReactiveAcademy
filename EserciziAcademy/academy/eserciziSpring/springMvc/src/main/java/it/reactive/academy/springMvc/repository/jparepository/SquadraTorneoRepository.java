package it.reactive.academy.springMvc.repository.jparepository;

import it.reactive.academy.springMvc.entity.SquadraEntity;
import it.reactive.academy.springMvc.entity.SquadraTorneoId;
import it.reactive.academy.springMvc.entity.SquadraTorneoEntity;
import it.reactive.academy.springMvc.entity.TorneoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface SquadraTorneoRepository extends JpaRepository<SquadraTorneoEntity, SquadraTorneoId> {
    Set<SquadraEntity> findByTorneoIdTorneo(Integer idTorneo);
    Set<TorneoEntity> findBySquadraIdSquadra(Integer idSquadra);
}
