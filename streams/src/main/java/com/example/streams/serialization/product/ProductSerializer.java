package com.example.streams.serialization.product;

import com.example.dtos.Product;
import com.google.gson.Gson;
import org.apache.kafka.common.serialization.Serializer;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class ProductSerializer implements Serializer<Product> {
    private final Gson GSON = new Gson();
    @Override
    public byte[] serialize(String topic, Product streamMessage) {
        return Objects.isNull(streamMessage) ? null : GSON.toJson(streamMessage).getBytes(StandardCharsets.UTF_8);
    }
}
