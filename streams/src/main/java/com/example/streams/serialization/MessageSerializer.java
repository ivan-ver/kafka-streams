package com.example.streams.serialization;

import com.example.dtos.StreamMessage;
import com.google.gson.Gson;
import org.apache.kafka.common.serialization.Serializer;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class MessageSerializer implements Serializer<StreamMessage> {
    private Gson gson = new Gson();
    @Override
    public byte[] serialize(String topic, StreamMessage streamMessage) {
        return Objects.isNull(streamMessage) ? null : gson.toJson(streamMessage).getBytes(StandardCharsets.UTF_8);
    }
}
