package com.example.streams;

import com.example.streams.serialization.scorePlayerProduct.ScorePlayerProductSerdes;
import org.apache.kafka.common.serialization.Serdes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.annotation.KafkaStreamsDefaultConfiguration;
import org.springframework.kafka.config.KafkaStreamsConfiguration;

import java.util.HashMap;

import static org.apache.kafka.streams.StreamsConfig.*;

@Configuration
@EnableKafka
@EnableKafkaStreams
public class BrokerConfig {
    @Bean(name = KafkaStreamsDefaultConfiguration.DEFAULT_STREAMS_CONFIG_BEAN_NAME)
    public KafkaStreamsConfiguration kStreamsConfig() {
        return new KafkaStreamsConfiguration(new HashMap<>(){{
            put(APPLICATION_ID_CONFIG, "some-group-st");
            put(BOOTSTRAP_SERVERS_CONFIG, "localhost:29092");
            put(DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
            put(DEFAULT_VALUE_SERDE_CLASS_CONFIG, ScorePlayerProductSerdes.class.getName());
        }});
    }
}
