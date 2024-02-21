package com.example.streams.serialization;

import com.example.dtos.StreamMessage;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

public class MessageSerdes implements Serde<StreamMessage> {
    @Override
    public Serializer<StreamMessage> serializer() {
        return new MessageSerializer();
    }

    @Override
    public Deserializer<StreamMessage> deserializer() {
        return new MessageDeserializer();
    }
}
