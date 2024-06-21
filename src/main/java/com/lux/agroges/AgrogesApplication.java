package com.lux.agroges;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@EnableJpaAuditing
public class AgrogesApplication {

    public static void main(String[] args) {
        SpringApplication.run(AgrogesApplication.class, args);
    }

}
