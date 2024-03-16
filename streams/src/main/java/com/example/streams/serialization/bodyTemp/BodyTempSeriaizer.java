package com.example.streams.serialization.bodyTemp;

import com.example.dtos.BodyTempEvent;
import com.google.gson.Gson;
import org.apache.kafka.common.serialization.Serializer;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class BodyTempSeriaizer implements Serializer<BodyTempEvent> {
    private final Gson GSON = new Gson();
    @Override
    public byte[] serialize(String topic, BodyTempEvent streamMessage) {
        return Objects.isNull(streamMessage) ? null : GSON.toJson(streamMessage).getBytes(StandardCharsets.UTF_8);
    }
}
