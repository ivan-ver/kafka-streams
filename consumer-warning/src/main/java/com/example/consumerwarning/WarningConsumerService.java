package com.example.consumerwarning;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
public class WarningConsumerService {
    @KafkaListener(topics = "warning-message")
    public void receiveMessage(@Payload String message, @Header(KafkaHeaders.RECEIVED_KEY) String key) {
        log.warn(message);
    }
}
