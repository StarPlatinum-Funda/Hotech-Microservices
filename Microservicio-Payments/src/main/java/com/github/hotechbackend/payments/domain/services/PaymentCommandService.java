package com.github.hotechbackend.payments.domain.services;

import com.github.hotechbackend.payments.domain.model.aggregates.Payment;
import com.github.hotechbackend.payments.domain.model.commands.CreatePaymentCommand;
import com.github.hotechbackend.payments.domain.model.commands.DeletePaymentCommand;
import com.github.hotechbackend.payments.domain.model.commands.UpdatePaymentCommand;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface PaymentCommandService {
    Optional<Payment> handle(CreatePaymentCommand command);
    Optional<Payment> handle(UpdatePaymentCommand command);
    void handle(DeletePaymentCommand command);
}
