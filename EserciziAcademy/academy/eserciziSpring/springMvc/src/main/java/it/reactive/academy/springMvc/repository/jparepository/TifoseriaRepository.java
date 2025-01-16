package it.reactive.academy.springMvc.repository.jparepository;

import it.reactive.academy.springMvc.entity.TifoseriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TifoseriaRepository extends JpaRepository<TifoseriaEntity, Integer> {
}
