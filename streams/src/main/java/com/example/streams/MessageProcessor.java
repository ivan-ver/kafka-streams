package com.example.streams;

import com.example.dtos.Player;
import com.example.dtos.ScoreEvent;
import com.example.dtos.ScoreResult;
import com.example.dtos.ScoreWithPlayer;
import com.example.streams.serialization.player.PlayerSerdes;
import com.example.streams.serialization.score.ScoreEventSerdes;
import com.example.streams.serialization.scoreWithPlayer.ScoreWithPlayerSerdes;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.Joined;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageProcessor {
    private static final Serde<String> STRING_SERDE = Serdes.String();
    private static final ScoreEventSerdes SCORE_EVENT_SERDE = new ScoreEventSerdes();
    private static final ScoreWithPlayerSerdes SCORE_WITH_PLAYER_SERDES = new ScoreWithPlayerSerdes();
    private static final PlayerSerdes PLAYER_SERDE = new PlayerSerdes();

    @Autowired
    void processMessage(StreamsBuilder streamsBuilder) {
        KStream<String, ScoreEvent> scoreEvents = streamsBuilder
                .stream("score", Consumed.with(STRING_SERDE, SCORE_EVENT_SERDE))
                .selectKey((key, value) -> value.playerId().toString());

        KTable<String, Player> playerKTable =
                streamsBuilder.table("player", Consumed.with(STRING_SERDE, PLAYER_SERDE));

        KStream<String, ScoreWithPlayer> withPlayers =
                scoreEvents.join(playerKTable, ScoreWithPlayer::new,
                        Joined.with(Serdes.String(), SCORE_EVENT_SERDE, PLAYER_SERDE));

        withPlayers.mapValues(val -> new ScoreResult(
                        val.player().id(),
                        val.player().name(),
                        val.scoreEvent().score()
                )).to("finish");
    }

    private void printData(String key, Object value) {
        System.out.println(key + " : " + value + " " + value.getClass().getName());
    }
}
