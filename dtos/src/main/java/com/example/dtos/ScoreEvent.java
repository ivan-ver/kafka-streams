package com.example.dtos;

public record ScoreEvent(
        Long playerId,
        Long ProductId,
        Double score
) {
}
