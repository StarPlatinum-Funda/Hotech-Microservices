package com.github.hotechbackend.payments.domain.model.commands;

import java.util.Date;

public record UpdatePaymentCommand(Long id, Long userId, float amount, float currency,
                                   String paymentMethod, String status, Date payment_date) {
    public UpdatePaymentCommand {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Id must be greater than 0");
        } else if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("User Id must be greater than 0");
        } else if (amount == 0) {
            throw new IllegalArgumentException("Amount must be greater than 0");
        } else if (currency == 0) {
            throw new IllegalArgumentException("Currency must be greater than 0");
        } else if (paymentMethod == null || paymentMethod.isBlank()) {
            throw new IllegalArgumentException("Payment Method cannot be null or blank");
        } else if (status == null || status.isBlank()) {
            throw new IllegalArgumentException("Status cannot be null or blank");
        } else if (payment_date == null) {
            throw new IllegalArgumentException("Payment Date cannot be null or blank");
        }
    }
}
