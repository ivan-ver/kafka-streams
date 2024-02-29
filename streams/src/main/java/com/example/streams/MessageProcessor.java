package com.example.streams;

import com.example.dtos.*;
import com.example.streams.serialization.player.PlayerSerdes;
import com.example.streams.serialization.product.ProductSerdes;
import com.example.streams.serialization.score.ScoreEventSerdes;
import com.example.streams.serialization.scorePlayerProduct.ScorePlayerProductSerdes;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageProcessor {
    private static final Serde<String> STRING_SERDE = Serdes.String();
    private static final ScoreEventSerdes SCORE_EVENT_SERDE = new ScoreEventSerdes();
    private static final PlayerSerdes PLAYER_SERDE = new PlayerSerdes();
    private static final ScorePlayerProductSerdes SCORE_PLAYER_PRODUCT_SERDE = new ScorePlayerProductSerdes();
    private static final ProductSerdes PRODUCT_SERDE = new ProductSerdes();

    @Autowired
    void processMessage(StreamsBuilder streamsBuilder) {
        KStream<String, ScoreEvent> scoreEvents = streamsBuilder
                .stream("score", Consumed.with(STRING_SERDE, SCORE_EVENT_SERDE))
                .selectKey((key, value) -> value.playerId().toString());

        KTable<String, Player> playerKTable =
                streamsBuilder.table("player", Consumed.with(STRING_SERDE, PLAYER_SERDE));

        GlobalKTable<String, Product> productGlobalKTable =
                streamsBuilder.globalTable("product", Consumed.with(STRING_SERDE, PRODUCT_SERDE));

        KStream<String, ScoreWithPlayer> withPlayers =
                scoreEvents.join(playerKTable, ScoreWithPlayer::new,
                        Joined.with(Serdes.String(), SCORE_EVENT_SERDE, PLAYER_SERDE));

        KStream<String, ScorePlayerProduct> allJoined =
                withPlayers.join(productGlobalKTable,
                        (leftKey, scoreWithPlayer) -> String.valueOf(scoreWithPlayer.scoreEvent().productId()),
                        (withPlayersVal, productVal) -> new ScorePlayerProduct(
                                withPlayersVal.scoreEvent(),
                                withPlayersVal.player(),
                                productVal)
                );

        allJoined.foreach(this::printData);

        allJoined.to("finish", Produced.with(Serdes.String(), SCORE_PLAYER_PRODUCT_SERDE));
    }

    private void printData(String key, Object value) {
        System.out.println(key + " : " + value + " " + value.getClass().getName());
    }
}
