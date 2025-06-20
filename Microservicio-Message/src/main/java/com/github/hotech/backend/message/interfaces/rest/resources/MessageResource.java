package com.github.hotech.backend.message.interfaces.rest.resources;

public record MessageResource(
        Long id,
        Long notificationId,
        Long userAccountId,
        String receptor,
        String sender,
        String content,
        String status
) {
}
