package com.example.producerpulse;

import com.example.dtos.PulseEvent;
import lombok.*;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProducerPulseService {
    private final static Random RND = new Random();
    private final KafkaTemplate<String, PulseEvent> kafkaTemplate;

    @SneakyThrows
    @Bean
    private void sendMessage() {
        String key = UUID.randomUUID().toString();
        while (true){
            PoulseLevel value = PoulseLevel.values()[RND.nextInt(PoulseLevel.values().length)];
            int period = RND.nextInt(30000, 90000);
            long start = new Date().getTime();
            while (new Date().getTime() < start + period) {
                long pulse = RND.nextInt(value.frequencyLow, value.frequencyHigh);
                kafkaTemplate.send("pulse-event", key, new PulseEvent(LocalDateTime.now().toString()));
                Thread.sleep(pulse);
            }
        }
    }

    @Getter
    @AllArgsConstructor
    private enum PoulseLevel{
        RARE(0, 60),
        MEDIUM(61, 90),
        HIGH(91, 200);

        private final int frequencyLow;
        private final int frequencyHigh;
    }
}
