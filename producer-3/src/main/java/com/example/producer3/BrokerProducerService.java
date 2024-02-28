package com.example.producer3;


import com.example.dtos.Product;
import com.example.dtos.ScoreEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BrokerProducerService {
    private final KafkaTemplate<String, Product> kafkaTemplate;
    private final static Random RND = new Random();

    @Scheduled(fixedDelay = 500)
    private void sendMessage() {
        RND.nextLong(20);
        Product product = new Product(RND.nextLong(10), "Product name " + RND.nextInt(100));
        kafkaTemplate.send("product", UUID.randomUUID().toString(), product);
    }
}
