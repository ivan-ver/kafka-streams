package com.example.streams.serialization.result;

import com.example.dtos.ScoreResult;
import com.google.gson.Gson;
import org.apache.kafka.common.serialization.Deserializer;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class ResultDeserializer implements Deserializer<ScoreResult> {
    private final Gson GSON = new Gson();

    @Override
    public ScoreResult deserialize(String topic, byte[] bytes) {
        return Objects.isNull(bytes) ? null : GSON.fromJson(new String(bytes, StandardCharsets.UTF_8), ScoreResult.class);
    }
}
