package com.github.hotech.backend.message.domain.model.commands;

public record CreateMessageCommand(
        Long id,
        String receptor,
        String sender,
        String content,
        Long notificationId,
        Long userAccountId
) {
}
