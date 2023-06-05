package com.example.accessingdatamysql;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.TransactionManager;
import org.springframework.boot.autoconfigure.transaction.TransactionManagerCustomizers;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.orm.jpa.JpaTransactionManager;

@Configuration(proxyBeanMethods = false)
public class JpaConfiguration {
    
    @Bean
    TransactionManager transactionManager(ObjectProvider<TransactionManagerCustomizers> transactionManagerCustomizers) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManagerCustomizers.ifAvailable((customizers) -> customizers.customize(transactionManager));
        return transactionManager;
    }
    
}
