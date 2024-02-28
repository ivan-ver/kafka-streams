package com.example.streams.serialization.player;

import com.example.dtos.Player;
import com.google.gson.Gson;
import org.apache.kafka.common.serialization.Serializer;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class PlayerSerializer implements Serializer<Player> {
    private final Gson GSON = new Gson();
    @Override
    public byte[] serialize(String topic, Player streamMessage) {
        return Objects.isNull(streamMessage) ? null : GSON.toJson(streamMessage).getBytes(StandardCharsets.UTF_8);
    }
}
