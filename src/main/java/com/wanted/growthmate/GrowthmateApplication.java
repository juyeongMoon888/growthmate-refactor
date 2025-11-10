package com.wanted.growthmate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class GrowthmateApplication {

    public static void main(String[] args) {
        SpringApplication.run(GrowthmateApplication.class, args);
    }

}
