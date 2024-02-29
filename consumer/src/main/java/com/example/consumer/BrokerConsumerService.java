package com.example.consumer;

import com.example.dtos.ScorePlayerProduct;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class BrokerConsumerService {

    @KafkaListener(topics = "finish")
    public void receiveMessage(@Payload ScorePlayerProduct message, @Header(KafkaHeaders.RECEIVED_KEY) String key) {
        System.out.printf("Message: %s with key %s%n", message, key);
    }
}
