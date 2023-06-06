package com.example.accessingdatamysql;

import java.lang.StackTraceElement;

import javax.sql.DataSource;

import org.springframework.core.io.ResourceLoader;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.TransactionManager;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.orm.jpa.JpaTransactionManager;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.AbstractJpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import org.springframework.boot.autoconfigure.transaction.TransactionManagerCustomizers;
import org.springframework.boot.autoconfigure.transaction.PlatformTransactionManagerCustomizer;
import org.springframework.boot.autoconfigure.transaction.TransactionProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;

@EnableJpaRepositories
@Configuration
public class DatabaseConfiguration {
    
    @Bean
    TransactionManager transactionManager(ObjectProvider<TransactionManagerCustomizers> transactionManagerCustomizers) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManagerCustomizers.ifAvailable((customizers) -> customizers.customize(transactionManager));
        return transactionManager;
    }

    @Bean
    public TransactionManagerCustomizers
        platformTransactionManagerCustomizers(ObjectProvider<PlatformTransactionManagerCustomizer<?>> customizers) {
        return new TransactionManagerCustomizers(customizers.orderedStream().toList());
    }

    @Bean
    TransactionProperties transactionProperties() {
        return new TransactionProperties();
    }

    @Bean
		DataSource dataSource() {        
        HikariDataSource dataSource = DataSourceBuilder.create()
            .type(HikariDataSource.class)
            .url(Application.dbUrl)
            .build();
				dataSource.setPoolName("default");
        return dataSource;
		}

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
    		AbstractJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setShowSql(true);
        return adapter;
    }

    // @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(ResourceLoader resourceLoader,
                                                                       DataSource dataSource,
                                                                       JpaVendorAdapter jpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();

        entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);
        entityManagerFactoryBean.setDataSource(dataSource);
        entityManagerFactoryBean.setPackagesToScan(new String[] {"com.example.accessingdatamysql"});

        return entityManagerFactoryBean;
    }


}
