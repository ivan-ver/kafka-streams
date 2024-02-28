package com.example.streams.serialization.product;

import com.example.dtos.Product;
import com.example.dtos.ScoreEvent;
import com.google.gson.Gson;
import org.apache.kafka.common.serialization.Deserializer;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class ProductDeserializer implements Deserializer<Product> {
    private Gson gson = new Gson();

    @Override
    public Product deserialize(String topic, byte[] bytes) {
        return Objects.isNull(bytes) ? null : gson.fromJson(new String(bytes, StandardCharsets.UTF_8), Product.class);
    }
}
