package com.example.accessingdatamysql;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.TransactionManager;
import org.springframework.boot.autoconfigure.transaction.TransactionManagerCustomizers;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.boot.autoconfigure.transaction.PlatformTransactionManagerCustomizer;
import org.springframework.boot.autoconfigure.transaction.TransactionProperties;

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
}
