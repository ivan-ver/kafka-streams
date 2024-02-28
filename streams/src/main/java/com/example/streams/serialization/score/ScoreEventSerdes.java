package com.example.streams.serialization.score;

import com.example.dtos.ScoreEvent;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

public class ScoreEventSerdes implements Serde<ScoreEvent> {
    @Override
    public Serializer<ScoreEvent> serializer() {
        return new ScoreEventSerializer();
    }

    @Override
    public Deserializer<ScoreEvent> deserializer() {
        return new ScoreEventDeserializer();
    }
}
