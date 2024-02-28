package com.example.dtos;

public record ScoreWithPlayer(
        ScoreEvent scoreEvent,
        Player player
) {
}
