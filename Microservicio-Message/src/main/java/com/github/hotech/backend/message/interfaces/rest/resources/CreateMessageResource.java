package com.github.hotech.backend.message.interfaces.rest.resources;

public record CreateMessageResource(
        Long id,
        String receptor,
        String sender,
        String content,
        Long notificationId,
        Long userAccountId
) {
    public CreateMessageResource {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("The message id cannot be null or 0");
        } else if (receptor == null || receptor.isBlank()) {
            throw new IllegalArgumentException("The receptor cannot be null or empty");
        } else if (sender == null || sender.isBlank()) {
            throw new IllegalArgumentException("The sender cannot be null or empty");
        } else if (content == null || content.isBlank()) {
            throw new IllegalArgumentException("The content cannot be null or empty");
        } else if (notificationId == null || notificationId <= 0) {
            throw new IllegalArgumentException("The notification id cannot be null or 0");
        } else if (userAccountId == null || userAccountId <= 0) {
            throw new IllegalArgumentException("The user account id cannot be null or 0");
        }
    }
}
