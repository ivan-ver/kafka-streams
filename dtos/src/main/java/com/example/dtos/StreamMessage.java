package com.example.dtos;

public record StreamMessage(
        String topic,
        String message,
        boolean isImportant
) {
}
