package com.example.accessingdatamysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyTool {
    
    @Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private UserRepository userRepository;

    @Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private Animal animal;

    @Autowired
    private ExoticAnimal exoticAnimal;
    
    public void run() {
        System.out.println("Called hello on animal and got: " + animal.hello());
        
        System.out.println("Called hello on the exotic animal and got: " + exoticAnimal.hello());

        userRepository.findAll().forEach(n -> {
                System.out.println("User: " + n.getName());
            });

    }
}
