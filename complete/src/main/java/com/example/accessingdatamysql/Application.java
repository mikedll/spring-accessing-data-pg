package com.example.accessingdatamysql;

import io.github.cdimascio.dotenv.Dotenv;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.ApplicationContext;

import org.springframework.boot.SpringApplication;

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
