package com.example.streams.serialization.scorePlayerProduct;

import com.example.dtos.ScorePlayerProduct;
import com.google.gson.Gson;
import org.apache.kafka.common.serialization.Deserializer;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class ScorePlayerProductDeserializer implements Deserializer<ScorePlayerProduct> {
    private final Gson GSON = new Gson();

    @Override
    public ScorePlayerProduct deserialize(String topic, byte[] bytes) {
        return Objects.isNull(bytes) ? null : GSON.fromJson(new String(bytes, StandardCharsets.UTF_8), ScorePlayerProduct.class);
    }
}
