package it.reactive.springbatch.configuration;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
    //@EnableTransactionManagement
    @EnableJpaRepositories(
            basePackages = "it.reactive.springbatch.repository",
            entityManagerFactoryRef = "entityManagerFactoryTorneo",
            transactionManagerRef = "transactionManagerTorneo"
    )
public class TorneoDatasourceConfig {



    @Bean("datasourceTorneo")
    @ConfigurationProperties(prefix = "spring.datasource-torneo")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "entityManagerFactoryTorneo")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(@Qualifier("datasourceTorneo") DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setPackagesToScan("it.reactive.springbatch.entity");
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        factoryBean.setJpaVendorAdapter(vendorAdapter);
        return factoryBean;
    }

    @Bean("transactionManagerTorneo")
    public PlatformTransactionManager transactionManager(@Qualifier("datasourceTorneo") DataSource dataSource) {
        return new JpaTransactionManager(entityManagerFactory(dataSource).getObject());
    }



}
