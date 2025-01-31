package it.reactive.springbatch.writer;

import it.reactive.springbatch.utility.Costanti;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.stereotype.Component;

@Component(Costanti.TORNEO_DB_WRITER)
public class TorneoFileWriter extends JpaItemWriter<Object> {

    public TorneoFileWriter(EntityManagerFactory entityManagerFactory) {
        this.setEntityManagerFactory(entityManagerFactory);
    }
}
