package com.example.streams;

import com.example.dtos.StreamMessage;
import com.example.streams.serialization.MessageSerdes;
import org.apache.kafka.common.protocol.types.Field;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.BranchedKStream;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageProcessor {
    private static final Serde<String> STRING_SERDE = Serdes.String();
    private static final MessageSerdes OBJECT_SERDE = new MessageSerdes();

    private static final Predicate<String, StreamMessage> POSITIVE = (key, value) -> value.isImportant();
    private static final Predicate<String, StreamMessage> NEGATIVE = (key, value) -> !value.isImportant();

    @Autowired
    void processMessage(StreamsBuilder streamsBuilder) {
        KStream<String, StreamMessage> messageStream = streamsBuilder
                .stream("process-data", Consumed.with(STRING_SERDE, OBJECT_SERDE));
        messageStream.foreach((key, value) -> System.out.println(key + ": " + value));
        KStream<String, StreamMessage> importantMessages = messageStream
                .filter(POSITIVE)
                .mapValues(value -> new StreamMessage("Важно!!!", value.message(), false));
        KStream<String, StreamMessage> notImportantMessages = messageStream.filter(NEGATIVE);
        KStream<String, StreamMessage> merged = importantMessages.merge(notImportantMessages);
        merged.to("receive3");
    }
}
