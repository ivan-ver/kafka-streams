package com.example.producerbodytemp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ProducerBodyTempApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProducerBodyTempApplication.class, args);
    }

}
