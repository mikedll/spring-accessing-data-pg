package com.example.accessingdatamysql;

import io.github.cdimascio.dotenv.Dotenv;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.ApplicationContext;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.transaction.TransactionAutoConfiguration;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
    
@SpringBootApplication(exclude = {TransactionAutoConfiguration.class, JpaRepositoriesAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
// these next two are redundant until we kill @SpringBootApplication
@ComponentScan
public class Application {
    
    public static String dbUrl;
    
    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();
        dbUrl = dotenv.get("DB_URL");
        ApplicationContext appCtx = SpringApplication.run(Application.class, args);

        // ApplicationContext appCtx = new AnnotationConfigApplicationContext(Application.class);
        // for (String beanName : appCtx.getBeanDefinitionNames()) {
        //     System.out.println(beanName);
        // }
        // System.out.println("Done printing beans");

        MyTool t = appCtx.getBean(MyTool.class);
        t.run();
    }

}
