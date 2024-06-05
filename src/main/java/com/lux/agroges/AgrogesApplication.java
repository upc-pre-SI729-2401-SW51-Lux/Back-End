package com.lux.agroges;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AgrogesApplication {

    public static void main(String[] args) {
        SpringApplication.run(AgrogesApplication.class, args);
    }

}
