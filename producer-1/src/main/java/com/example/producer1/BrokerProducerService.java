package com.example.producer1;


import com.example.dtos.StreamMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BrokerProducerService {
    private final KafkaTemplate<String, StreamMessage> kafkaTemplate;
    private final static Random RND = new Random();

    @Scheduled(fixedDelay = 2000)
    private void sendMessage() {
        int number = RND.nextInt(10) + 1;
        StreamMessage message = new StreamMessage("some topic", "Some message", number > 5);
        kafkaTemplate.send("process-data", UUID.randomUUID().toString(), message);
    }
}
