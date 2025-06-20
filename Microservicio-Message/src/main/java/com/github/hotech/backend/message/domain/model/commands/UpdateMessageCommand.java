package com.github.hotech.backend.message.domain.model.commands;

public record UpdateMessageCommand(Long id,
                                   String receptor,
                                   String sender,
                                   String content,
                                   Long notificationId,
                                   Long userAccountId) {

    public UpdateMessageCommand {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Id must be greater than 0");
        }

        if (receptor == null || receptor.isBlank()) {
            throw new IllegalArgumentException("Receptor cannot be null or empty");
        }

        if (sender == null || sender.isBlank()) {
            throw new IllegalArgumentException("Sender cannot be null or empty");
        }

        if (content == null || content.isBlank()) {
            throw new IllegalArgumentException("Content cannot be null or empty");
        }

        if (notificationId == null || notificationId <= 0) {
            throw new IllegalArgumentException("Notification Id must be greater than 0");
        }

        if (userAccountId == null || userAccountId <= 0) {
            throw new IllegalArgumentException("User Account Id must be greater than 0");
        }
    }
}
