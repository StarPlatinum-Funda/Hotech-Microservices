package com.github.hotech.backend.message.domain.model.commands;

public record MarkMessageAsReadCommand(
        Long messageId
) {
}
