package it.reactive.springbatch.configuration;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

    @Configuration
    @EnableTransactionManagement
    @EnableJpaRepositories(
            basePackages = "it.reactive.springbatch.repository",
            entityManagerFactoryRef = "torneoEntityManager",
            transactionManagerRef = "torneoTransactionManager"
    )
    public class TorneoDatasourceConfig {

        public static final String DATA_SOURCE_TORNEO = "dataSource-torneo";

        @Bean(name = DATA_SOURCE_TORNEO)
        @ConfigurationProperties(prefix = "spring.datasource.torneo")
        public DataSource torneoDatasource() {
            return DataSourceBuilder.create().build();
        }

        @Bean(name = "torneoEntityManager")
        public LocalContainerEntityManagerFactoryBean entityManagerFactory(
                @Qualifier(DATA_SOURCE_TORNEO) DataSource datasource) {
            LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
            em.setDataSource(datasource);
            em.setPackagesToScan("it.reactive.springbatch.entity");

            HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
            em.setJpaVendorAdapter(vendorAdapter);
            return em;
        }

        @Bean(name = "torneoTransactionManager")
        public PlatformTransactionManager transactionManager(
                @Qualifier("torneoEntityManager") EntityManagerFactory emf) {
            return new JpaTransactionManager(emf);
        }
    }
