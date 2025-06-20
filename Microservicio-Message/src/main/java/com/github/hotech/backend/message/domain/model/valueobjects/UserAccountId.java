package com.github.hotech.backend.message.domain.model.valueobjects;

public record UserAccountId(long userAccountId) {

    public UserAccountId {
        if (userAccountId <= 0) {
            throw new IllegalArgumentException("User Account ID must be greater than 0");

        }
    }

    public UserAccountId() {
        this(0L);
    }
}
