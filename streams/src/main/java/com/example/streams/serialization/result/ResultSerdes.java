package com.example.streams.serialization.result;

import com.example.dtos.ScoreResult;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

public class ResultSerdes implements Serde<ScoreResult> {
    @Override
    public Serializer<ScoreResult> serializer() {
        return new ResultSerializer();
    }

    @Override
    public Deserializer<ScoreResult> deserializer() {
        return new ResultDeserializer();
    }
}
