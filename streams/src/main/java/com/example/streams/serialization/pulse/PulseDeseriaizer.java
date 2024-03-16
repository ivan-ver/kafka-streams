package com.example.streams.serialization.pulse;

import com.example.dtos.PulseEvent;
import com.google.gson.Gson;
import org.apache.kafka.common.serialization.Deserializer;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class PulseDeseriaizer implements Deserializer<PulseEvent> {
    private final Gson GSON = new Gson();

    @Override
    public PulseEvent deserialize(String topic, byte[] bytes) {
        return Objects.isNull(bytes) ? null : GSON.fromJson(new String(bytes, StandardCharsets.UTF_8), PulseEvent.class);
    }
}