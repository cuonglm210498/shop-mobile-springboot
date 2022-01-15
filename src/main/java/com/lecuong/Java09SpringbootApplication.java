package com.lecuong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Java09SpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(Java09SpringbootApplication.class, args);
    }

}
