package com.github.hotechbackend.payments.interfaces.rest.resources;

import java.util.Date;

public record PaymentResource(Long id,
                              Long userId,
                              float amount,
                              float currency,
                              String paymentMethod,
                              String status,
                              Date payment_date) {
}
