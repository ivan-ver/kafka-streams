package com.example.streams.serialization.player;

import com.example.dtos.Player;
import com.example.dtos.Product;
import com.google.gson.Gson;
import org.apache.kafka.common.serialization.Deserializer;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class PlayerDeserializer implements Deserializer<Player> {
    private final Gson GSON = new Gson();

    @Override
    public Player deserialize(String topic, byte[] bytes) {
        return Objects.isNull(bytes) ? null : GSON.fromJson(new String(bytes, StandardCharsets.UTF_8), Player.class);
    }
}
