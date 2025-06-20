package com.github.hotechbackend.payments.domain.model.valueobjects;

public record PaymentMethod(String paymentMethod) {
    public PaymentMethod {
        if(paymentMethod == null || paymentMethod.isBlank())
            throw new IllegalArgumentException("Payment Method cannot be null or blank.");
    }
}
