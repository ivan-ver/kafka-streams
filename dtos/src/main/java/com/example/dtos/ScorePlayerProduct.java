package com.example.dtos;

public record ScorePlayerProduct(
        ScoreEvent scoreEvent,
        Player player,
        Product product
) {
}
