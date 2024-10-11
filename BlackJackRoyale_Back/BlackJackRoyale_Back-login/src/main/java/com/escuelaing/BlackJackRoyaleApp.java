package com.escuelaing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.escuelaing.repository")
public class BlackJackRoyaleApp {
    public static void main(String[] args) {
        SpringApplication.run(BlackJackRoyaleApp.class, args);
    }
}

