package com.example.consumernorm;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NormalConsumerService {
    @KafkaListener(topics = "normal-message")
    public void receiveMessage(@Payload String message, @Header(KafkaHeaders.RECEIVED_KEY) String key) {
        log.info(message);
    }
}
