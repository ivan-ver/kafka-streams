package com.example.streams.serialization.scorePlayerProduct;

import com.example.dtos.ScorePlayerProduct;
import com.google.gson.Gson;
import org.apache.kafka.common.serialization.Serializer;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class ScorePlayerProductSerializer implements Serializer<ScorePlayerProduct> {
    private final Gson GSON = new Gson();
    @Override
    public byte[] serialize(String topic, ScorePlayerProduct streamMessage) {
        return Objects.isNull(streamMessage) ? null : GSON.toJson(streamMessage).getBytes(StandardCharsets.UTF_8);
    }
}
