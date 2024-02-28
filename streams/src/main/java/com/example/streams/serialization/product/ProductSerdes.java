package com.example.streams.serialization.product;

import com.example.dtos.Product;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

public class ProductSerdes implements Serde<Product> {
    @Override
    public Serializer<Product> serializer() {
        return new ProductSerializer();
    }

    @Override
    public Deserializer<Product> deserializer() {
        return new ProductDeserializer();
    }
}
