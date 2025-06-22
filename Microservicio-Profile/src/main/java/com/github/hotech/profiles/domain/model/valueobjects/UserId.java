package com.github.hotech.profiles.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record UserId(Long userId) {
    public UserId() {
        this(null);
    }
    public UserId {
        if (userId == null)
            throw new IllegalArgumentException("User id cannot be null");
    }

}
