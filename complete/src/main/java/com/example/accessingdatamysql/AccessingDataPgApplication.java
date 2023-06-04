package com.example.accessingdatamysql;

import javax.sql.DataSource;

import io.github.cdimascio.dotenv.Dotenv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.jdbc.JdbcConnectionDetails;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.jdbc.DatabaseDriver;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
// these next two are redundant until we kill @SpringBootApplication
@ComponentScan
@Configuration
public class AccessingDataPgApplication {

    private static String dbUrl;
    
    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();
        dbUrl = dotenv.get("DB_URL");
        // SpringApplication.run(AccessingDataPgApplication.class, args);

        ApplicationContext appCtx = new AnnotationConfigApplicationContext(AccessingDataPgApplication.class);
        for (String beanName : appCtx.getBeanDefinitionNames()) {
            System.out.println(beanName);
        }
        System.out.println("Done printing beans");

        MyTool t = appCtx.getBean(MyTool.class);
        t.run();
    }

    @Bean
		DataSource dataSource() {        
        HikariDataSource dataSource = DataSourceBuilder.create()
            .type(HikariDataSource.class)
            .url(dbUrl)
            .build();
				dataSource.setPoolName("default");
        return dataSource;
		}
    
}
