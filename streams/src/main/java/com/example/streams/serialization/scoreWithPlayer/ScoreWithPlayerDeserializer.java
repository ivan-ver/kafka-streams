package com.example.streams.serialization.scoreWithPlayer;

import com.example.dtos.ScoreWithPlayer;
import com.google.gson.Gson;
import org.apache.kafka.common.serialization.Deserializer;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class ScoreWithPlayerDeserializer implements Deserializer<ScoreWithPlayer> {
    private final Gson GSON = new Gson();

    @Override
    public ScoreWithPlayer deserialize(String topic, byte[] bytes) {
        return Objects.isNull(bytes) ? null : GSON.fromJson(new String(bytes, StandardCharsets.UTF_8), ScoreWithPlayer.class);
    }
}
