package com.example.streams.serialization.scoreWithPlayer;

import com.example.dtos.ScoreWithPlayer;
import com.google.gson.Gson;
import org.apache.kafka.common.serialization.Serializer;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class ScoreWithPlayerSerializer implements Serializer<ScoreWithPlayer> {
    private final Gson GSON = new Gson();
    @Override
    public byte[] serialize(String topic, ScoreWithPlayer streamMessage) {
        return Objects.isNull(streamMessage) ? null : GSON.toJson(streamMessage).getBytes(StandardCharsets.UTF_8);
    }
}
