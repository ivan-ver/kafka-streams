package com.example.streams.serialization.result;

import com.example.dtos.ScoreResult;
import com.google.gson.Gson;
import org.apache.kafka.common.serialization.Serializer;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class ResultSerializer implements Serializer<ScoreResult> {
    private final Gson GSON = new Gson();
    @Override
    public byte[] serialize(String topic, ScoreResult streamMessage) {
        return Objects.isNull(streamMessage) ? null : GSON.toJson(streamMessage).getBytes(StandardCharsets.UTF_8);
    }
}
