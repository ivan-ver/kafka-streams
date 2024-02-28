package com.example.streams.serialization.scoreWithPlayer;

import com.example.dtos.ScoreWithPlayer;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

public class ScoreWithPlayerSerdes implements Serde<ScoreWithPlayer> {
    @Override
    public Serializer<ScoreWithPlayer> serializer() {
        return new ScoreWithPlayerSerializer();
    }

    @Override
    public Deserializer<ScoreWithPlayer> deserializer() {
        return new ScoreWithPlayerDeserializer();
    }
}
