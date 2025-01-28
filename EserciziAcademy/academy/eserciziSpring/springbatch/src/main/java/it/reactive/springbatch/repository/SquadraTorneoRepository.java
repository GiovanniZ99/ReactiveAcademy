package it.reactive.springbatch.repository;

import it.reactive.springbatch.entity.SquadraTorneoEntity;
import it.reactive.springbatch.entity.SquadraTorneoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

public interface SquadraTorneoRepository extends JpaRepository<SquadraTorneoEntity, SquadraTorneoId> {
    Set<SquadraTorneoEntity> findByTorneoIdTorneo(Integer idTorneo);

    Set<SquadraTorneoEntity> findBySquadraIdSquadra(Integer idSquadra);

    @Transactional
    @Modifying
    @Query("delete from SquadraTorneoEntity st where st.squadra.idSquadra = :idSquadra")
    void deleteBySquadraIdSquadra(@Param("idSquadra") Integer idSquadra);
}
