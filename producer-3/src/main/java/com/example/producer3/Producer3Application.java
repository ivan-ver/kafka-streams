package com.example.producer3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Producer3Application {

    public static void main(String[] args) {
        SpringApplication.run(Producer3Application.class, args);
    }

}
