package com.example.streams.serialization.scorePlayerProduct;

import com.example.dtos.ScorePlayerProduct;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

public class ScorePlayerProductSerdes implements Serde<ScorePlayerProduct> {
    @Override
    public Serializer<ScorePlayerProduct> serializer() {
        return new ScorePlayerProductSerializer();
    }

    @Override
    public Deserializer<ScorePlayerProduct> deserializer() {
        return new ScorePlayerProductDeserializer();
    }
}
