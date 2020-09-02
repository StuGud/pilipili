package com.seu.pilipili;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PilipiliApplication {

    public static void main(String[] args) {
        SpringApplication.run(PilipiliApplication.class, args);
    }

}
