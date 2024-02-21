package com.example.streams.serialization;

import com.example.dtos.StreamMessage;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.kafka.common.serialization.Deserializer;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class MessageDeserializer implements Deserializer<StreamMessage> {
    private Gson gson = new Gson();

    @Override
    public StreamMessage deserialize(String topic, byte[] bytes) {
        return Objects.isNull(bytes) ? null : gson.fromJson(
                new String(bytes, StandardCharsets.UTF_8), StreamMessage.class
        );
    }
}
