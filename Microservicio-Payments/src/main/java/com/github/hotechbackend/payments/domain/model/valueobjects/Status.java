package com.github.hotechbackend.payments.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record Status(String status) {
    public Status {
        if (status == null || status.isBlank())
            throw new IllegalArgumentException("Status cannot be null or blank");
    }
}
