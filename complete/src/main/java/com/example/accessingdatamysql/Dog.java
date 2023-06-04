package com.example.accessingdatamysql;

import org.springframework.stereotype.Component;

@Component
public class Dog implements Animal {

    public Dog() {
        System.out.println("Dog constructor called");
    }
    
    public String hello() {
        return "Woof Woof";
    }
    
}
