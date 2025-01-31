package it.reactive.springbatch.configuration;

import it.reactive.springbatch.utility.Costanti;
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

        @Bean(name = Costanti.DATA_SOURCE_TORNEO)
        @ConfigurationProperties(prefix = Costanti.PREFIX_PROPERTIES)
        public DataSource torneoDatasource() {
            return DataSourceBuilder.create().build();
        }

        @Bean(name = Costanti.ENTITY_MANAGER_TORNEO)
        public LocalContainerEntityManagerFactoryBean entityManagerFactory(
                @Qualifier(Costanti.DATA_SOURCE_TORNEO) DataSource datasource) {
            LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
            em.setDataSource(datasource);
            em.setPackagesToScan(Costanti.ENTITIES_PACKAGE);

            HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
            em.setJpaVendorAdapter(vendorAdapter);
            return em;
        }

        @Bean(name = Costanti.TORNEO_TRANSACTION_MANAGER)
        public PlatformTransactionManager transactionManager(
                @Qualifier(Costanti.ENTITY_MANAGER_TORNEO) EntityManagerFactory emf) {
            return new JpaTransactionManager(emf);
        }
    }
