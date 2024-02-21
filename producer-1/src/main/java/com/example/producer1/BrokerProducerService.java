package com.example.producer1;


import com.example.dtos.StreamMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BrokerProducerService {
    private final KafkaTemplate<String, StreamMessage> kafkaTemplate;

    @Scheduled(fixedDelay = 2000)
    private void sendMessage() {
        StreamMessage message = new StreamMessage("some topic", "Some message", true);
        kafkaTemplate.send("process-data", UUID.randomUUID().toString(), message);
    }
}
