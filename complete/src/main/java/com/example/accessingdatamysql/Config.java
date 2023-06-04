package com.example.accessingdatamysql;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

@Configuration
public class Config {

    @Bean
    public ExoticAnimal exoticAnimal() {
        System.out.println("Constructing an exotic animal");
        return new ExoticAnimal();
    }
}
