package com.example.accessingdatamysql;

import org.springframework.stereotype.Component;

@Component
public class Dog implements Animal {
    
    public String hello() {
        return "Woof Woof";
    }
    
}
