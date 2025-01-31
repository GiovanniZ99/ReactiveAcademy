package it.reactive.academy.springMvc.repository.jparepository;

import it.reactive.academy.springMvc.entity.GiocatoreEntity;
import it.reactive.academy.springMvc.entity.SquadraEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;

public interface GiocatoreRepository extends JpaRepository<GiocatoreEntity, Integer> {
    Set<Optional<GiocatoreEntity>> findAllBySquadra(SquadraEntity squadra);
    Optional<GiocatoreEntity> findByNomeCognome(String nomeGiocatore);

    @Transactional
    @Modifying
    @Query("delete from GiocatoreEntity g where g.squadra.idSquadra = :idSquadra")
    void deleteBySquadraIdSquadra(@Param("idSquadra") Integer idSquadra);
}
