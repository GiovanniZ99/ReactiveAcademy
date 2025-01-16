package it.reactive.academy.springMvc.repository.jparepository;

import it.reactive.academy.springMvc.entity.GiocatoreEntity;
import it.reactive.academy.springMvc.entity.SquadraEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface GiocatoreRepository extends JpaRepository<GiocatoreEntity, Integer> {
    Set<Optional<GiocatoreEntity>> findAllBySquadra(SquadraEntity squadra);
    Optional<GiocatoreEntity> findByNomeCognome(String nomeGiocatore);
}
