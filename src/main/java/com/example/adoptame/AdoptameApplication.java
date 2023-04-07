package com.example.adoptame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class AdoptameApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdoptameApplication.class, args);
    }

}
