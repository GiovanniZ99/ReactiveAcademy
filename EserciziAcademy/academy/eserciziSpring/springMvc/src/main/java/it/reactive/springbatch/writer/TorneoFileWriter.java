package it.reactive.springbatch.writer;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TorneoFileWriter {

    private final EntityManagerFactory entityManagerFactory;

    public TorneoFileWriter( @Qualifier("entityManagerFactoryTorneo") EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Bean
    public JpaItemWriter<Object> jpaWriter() {
        JpaItemWriter<Object> writer = new JpaItemWriter<>();
        writer.setEntityManagerFactory(entityManagerFactory);
        return writer;
    }
}
