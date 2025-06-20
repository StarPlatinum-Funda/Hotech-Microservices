package com.github.hotech.backend.message.domain.model.valueobjects;

public record NotificationId(Long notificationId) {
    public NotificationId() {
        this(null);
    }
    public NotificationId {
        if (notificationId == null)
            throw new IllegalArgumentException("Notification id cannot be null");
    }
}
