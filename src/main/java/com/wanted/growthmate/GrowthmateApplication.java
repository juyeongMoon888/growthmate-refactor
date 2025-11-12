package com.wanted.growthmate;

import com.wanted.growthmate.user.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.wanted.growthmate")
public class GrowthmateApplication {

    public static void main(String[] args) {
        SpringApplication.run(GrowthmateApplication.class, args);
    }

}
