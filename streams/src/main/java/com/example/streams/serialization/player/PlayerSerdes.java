package com.example.streams.serialization.player;

import com.example.dtos.Player;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

public class PlayerSerdes implements Serde<Player> {
    @Override
    public Serializer<Player> serializer() {
        return new PlayerSerializer();
    }

    @Override
    public Deserializer<Player> deserializer() {
        return new PlayerDeserializer();
    }
}
