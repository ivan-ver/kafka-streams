package com.example.streams;

import com.example.dtos.Vital;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.streams.processor.TimestampExtractor;

import java.time.Instant;
import java.util.Objects;
import java.util.Optional;

public class VitalTimeStampExtractor implements TimestampExtractor {

    @Override
    public long extract(ConsumerRecord<Object, Object> consumerRecord, long l) {
        Vital vital = (Vital) consumerRecord.value();
        return Optional.of(vital)
                .filter(vit -> Objects.nonNull(vit.getTimeStamp()))
                .map(vit -> Instant.parse(vit.getTimeStamp() + "Z").toEpochMilli())
                .orElse(System.currentTimeMillis());
    }
}
