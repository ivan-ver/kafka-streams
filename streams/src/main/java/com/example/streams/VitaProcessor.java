package com.example.streams;

import com.example.streams.serialization.bodyTemp.BodyTempSerdes;
import com.example.streams.serialization.pulse.PulseSerdes;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Branched;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.TimeWindows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class VitaProcessor {
    @Autowired
    void processPulseMessage(StreamsBuilder streamsBuilder) {
        streamsBuilder.stream("pulse-event", Consumed
                        .with(Serdes.String(), new PulseSerdes())
                        .withTimestampExtractor(new VitalTimeStampExtractor()))
                .groupByKey()
                .windowedBy(TimeWindows.ofSizeAndGrace(Duration.ofSeconds(60), Duration.ofSeconds(5)))
                .count()
                .toStream()
                .split()
                .branch((key, value) -> value > 100, Branched.withConsumer((ks) -> ks
                        .mapValues((val) -> "Pulse is high!!! %s beats per minute".formatted(val))
                        .to("warning-message")))
                .branch((key, value) -> value <= 100, Branched.withConsumer((ks) -> ks
                        .mapValues((val) -> "Pulse is normal: %s beats per minute".formatted(val))
                        .to("normal-message")));
    }

    @Autowired
    void processTemperatureMessage(StreamsBuilder streamsBuilder) {
        streamsBuilder.stream("body-temp-event", Consumed.with(Serdes.String(), new BodyTempSerdes()))
                .split()
                .branch((key, value) -> value.getValue() > 37, Branched.withConsumer((ks) -> ks
                        .mapValues((val) -> "Temperature is high!!! %s celsium".formatted(val.getValue()))
                        .to("warning-message")))
                .branch((key, value) -> value.getValue() <= 37 && value.getValue() >= 36, Branched.withConsumer((ks) -> ks
                        .mapValues((val) -> "Temperature is normal: %s celsium".formatted(val.getValue()))
                        .to("normal-message")))
                .branch((key, value) -> value.getValue() > 36, Branched.withConsumer((ks) -> ks
                        .mapValues((val) -> "Temperature is low!!! %s celsium".formatted(val.getValue()))
                        .to("warning-message")));

    }

}
