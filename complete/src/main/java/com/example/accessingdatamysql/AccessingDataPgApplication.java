package com.example.accessingdatamysql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

    
// @SpringBootApplication
@ComponentScan
public class AccessingDataPgApplication {
    
    public static void main(String[] args) {
        // SpringApplication.run(AccessingDataPgApplication.class, args);

        AnnotationConfigApplicationContext appCtx = new AnnotationConfigApplicationContext(AccessingDataPgApplication.class);
        for (String beanName : appCtx.getBeanDefinitionNames()) {
            System.out.println(beanName);
        }
        System.out.println("Done printing beans");
        
        MyTool t = appCtx.getBean(MyTool.class);
        t.run();
    }
}
