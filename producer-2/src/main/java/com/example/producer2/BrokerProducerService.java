package com.example.producer2;



import com.example.dtos.Player;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BrokerProducerService {
    private final KafkaTemplate<String, Player> kafkaTemplate;
    private final static Random RND = new Random();

    @Scheduled(fixedDelay = 500)
    private void sendMessage() {
        RND.nextLong(20);
        long id = RND.nextLong(100);
        Player player = new Player(id, "User name " + id);
        kafkaTemplate.send("player", String.valueOf(id), player);
    }
}
