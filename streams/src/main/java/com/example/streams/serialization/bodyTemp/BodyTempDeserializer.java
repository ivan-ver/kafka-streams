package com.example.streams.serialization.bodyTemp;

import com.example.dtos.BodyTempEvent;
import com.google.gson.Gson;
import org.apache.kafka.common.serialization.Deserializer;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class BodyTempDeserializer implements Deserializer<BodyTempEvent> {
    private final Gson GSON = new Gson();

    @Override
    public BodyTempEvent deserialize(String topic, byte[] bytes) {
        return Objects.isNull(bytes) ? null : GSON.fromJson(new String(bytes, StandardCharsets.UTF_8), BodyTempEvent.class);
    }
}