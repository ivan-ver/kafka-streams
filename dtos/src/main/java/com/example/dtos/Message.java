package com.example.dtos;

public record Message(
        String topic,
        String message,
        boolean isImportant
) {
}
