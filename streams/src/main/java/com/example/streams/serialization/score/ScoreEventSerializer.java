package com.example.streams.serialization.score;

import com.example.dtos.ScoreEvent;
import com.google.gson.Gson;
import org.apache.kafka.common.serialization.Serializer;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class ScoreEventSerializer implements Serializer<ScoreEvent> {
    private final Gson GSON = new Gson();
    @Override
    public byte[] serialize(String topic, ScoreEvent streamMessage) {
        return Objects.isNull(streamMessage) ? null : GSON.toJson(streamMessage).getBytes(StandardCharsets.UTF_8);
    }
}
