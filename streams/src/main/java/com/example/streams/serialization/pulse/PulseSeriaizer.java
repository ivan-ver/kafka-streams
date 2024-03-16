package com.example.streams.serialization.pulse;

import com.example.dtos.PulseEvent;
import com.google.gson.Gson;
import org.apache.kafka.common.serialization.Serializer;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class PulseSeriaizer implements Serializer<PulseEvent> {
    private final Gson GSON = new Gson();
    @Override
    public byte[] serialize(String topic, PulseEvent streamMessage) {
        return Objects.isNull(streamMessage) ? null : GSON.toJson(streamMessage).getBytes(StandardCharsets.UTF_8);
    }
}
