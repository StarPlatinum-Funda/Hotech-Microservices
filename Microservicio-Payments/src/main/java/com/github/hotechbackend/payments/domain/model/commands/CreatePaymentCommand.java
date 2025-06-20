package com.github.hotechbackend.payments.domain.model.commands;

import java.util.Date;

public record CreatePaymentCommand(Long id, Long userId, float amount, float currency,
                                   String paymentMethod, String status, Date payment_date) {
}
