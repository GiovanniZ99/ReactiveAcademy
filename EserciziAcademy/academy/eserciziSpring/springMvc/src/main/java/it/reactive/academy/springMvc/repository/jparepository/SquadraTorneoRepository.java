package it.reactive.academy.springMvc.repository.jparepository;

import it.reactive.academy.springMvc.entity.SquadraTorneoEntity;
import it.reactive.academy.springMvc.entity.SquadraTorneoJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface SquadraTorneoRepository extends JpaRepository<SquadraTorneoJpa, SquadraTorneoEntity> {
    @Query("SELECT st.id.idSquadra FROM SquadraTorneoJpa st WHERE st.id.idTorneo = :idTorneo")
    Set<Integer> findAllByIdTorneo(@Param("idTorneo") Integer idTorneo);}
