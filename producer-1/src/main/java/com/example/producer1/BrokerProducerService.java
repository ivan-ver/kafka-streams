package com.example.producer1;


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
    private final KafkaTemplate<String, ScoreEvent> kafkaTemplate;
    private final static Random RND = new Random();

    @Scheduled(fixedDelay = 500)
    private void sendMessage() {
        RND.nextLong(20);
        ScoreEvent scoreEvent = new ScoreEvent(RND.nextLong(50), RND.nextLong(10), RND.nextDouble(100));
        kafkaTemplate.send("score", UUID.randomUUID().toString(), scoreEvent);
    }
}
