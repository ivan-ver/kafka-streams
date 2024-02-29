package com.example.dtos;

public record ScoreEvent(
        Long playerId,
        Long productId,
        Double score
) {
}
