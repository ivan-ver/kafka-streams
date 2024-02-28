package com.example.streams.serialization.score;

import com.example.dtos.ScoreEvent;
import com.google.gson.Gson;
import org.apache.kafka.common.serialization.Deserializer;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class ScoreEventDeserializer implements Deserializer<ScoreEvent> {
    private final Gson GSON = new Gson();

    @Override
    public ScoreEvent deserialize(String topic, byte[] bytes) {
        return Objects.isNull(bytes) ? null : GSON.fromJson(new String(bytes, StandardCharsets.UTF_8), ScoreEvent.class);
    }
}
