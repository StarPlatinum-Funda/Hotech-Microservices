package com.github.hotechbackend.payments.domain.model.valueobjects;

public record UserId(Long userId) {
    public UserId() {
        this(null);
    }
    public UserId {
        if (userId == null)
            throw new IllegalArgumentException("User id cannot be null");
    }
}
