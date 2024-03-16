package com.example.streams.serialization.bodyTemp;

import com.example.dtos.BodyTempEvent;
import com.example.dtos.PulseEvent;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

public class BodyTempSerdes implements Serde<BodyTempEvent> {
    @Override
    public Serializer<BodyTempEvent> serializer() {
        return new BodyTempSeriaizer();
    }

    @Override
    public Deserializer<BodyTempEvent> deserializer() {
        return new BodyTempDeserializer();
    }
}
