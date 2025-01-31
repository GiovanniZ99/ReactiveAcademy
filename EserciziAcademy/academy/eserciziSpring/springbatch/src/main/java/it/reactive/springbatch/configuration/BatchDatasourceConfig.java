package it.reactive.springbatch.configuration;

import it.reactive.springbatch.utility.Costanti;
import org.springframework.boot.autoconfigure.batch.BatchDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
public class BatchDatasourceConfig {

    @Primary
    @Bean(name = Costanti.H2_DATASOURCE)
    @BatchDataSource
    public DataSource h2Datasource() {
        return new EmbeddedDatabaseBuilder()
                .addScript(Costanti.H2_CREATE_SCHEMA)
                .addScript(Costanti.H2_DROP_SCHEMA)
                .setType(EmbeddedDatabaseType.H2)
                .build();
    }
}
