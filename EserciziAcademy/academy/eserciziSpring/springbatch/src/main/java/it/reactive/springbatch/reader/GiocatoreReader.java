package it.reactive.springbatch.reader;

import it.reactive.springbatch.entity.GiocatoreEntity;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.stereotype.Component;

@Component
public class GiocatoreReader extends JpaPagingItemReader<GiocatoreEntity> {

    public GiocatoreReader(EntityManagerFactory entityManagerFactory) {
        this.setEntityManagerFactory(entityManagerFactory);
        this.setQueryString("SELECT g FROM GiocatoreEntity g");
        this.setPageSize(10);
    }
}
