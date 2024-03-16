package com.example.streams.serialization.pulse;

import com.example.dtos.PulseEvent;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

public class PulseSerdes implements Serde<PulseEvent> {
    @Override
    public Serializer<PulseEvent> serializer() {
        return new PulseSeriaizer();
    }

    @Override
    public Deserializer<PulseEvent> deserializer() {
        return new PulseDeseriaizer();
    }
}
