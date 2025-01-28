package it.reactive.springbatch.repository;

import it.reactive.springbatch.entity.TifoseriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface TifoseriaRepository extends JpaRepository<TifoseriaEntity, Integer> {
   TifoseriaEntity findBySquadraIdSquadra(Integer id);

   @Transactional
   @Modifying
   @Query("delete from TifoseriaEntity t where t.squadra.idSquadra = :idSquadra")
   void deleteBySquadraIdSquadra(@Param("idSquadra") Integer idSquadra);
}
