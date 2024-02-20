package com.example.producer1;


import com.example.dtos.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BrokerProducerService {
    private final KafkaTemplate<String, Message> kafkaTemplate;

    @Scheduled(fixedDelay = 100)
    private void sendMessage() {
        Message message = new Message("some topic", "Some message", true);
        kafkaTemplate.send("streams-topic-send-1", UUID.randomUUID().toString(), message);
    }
}
