package com.example.producerbodytemp;

import com.example.dtos.BodyTempEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BodyTempService {
    private final KafkaTemplate<String, BodyTempEvent> kafkaTemplate;
    private final static Random RND = new Random();

    @Scheduled(fixedDelay = 1000)
    private void sendMessage() {
        kafkaTemplate.send("body-temp-event",
                UUID.randomUUID().toString(),
                new BodyTempEvent(
                LocalDateTime.now().toString(),
                RND.nextDouble(35.5, 42.5),
                "Celsium"
        ));
    }
}
