package it.reactive.springbatch.repository;

import it.reactive.springbatch.entity.GiocatoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GiocatoreRepository extends JpaRepository<GiocatoreEntity, Long> {
}
