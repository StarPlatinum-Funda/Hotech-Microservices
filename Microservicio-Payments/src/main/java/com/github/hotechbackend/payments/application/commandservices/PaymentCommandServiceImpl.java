package com.github.hotechbackend.payments.application.commandservices;

import com.github.hotechbackend.payments.domain.model.aggregates.Payment;
import com.github.hotechbackend.payments.domain.model.commands.CreatePaymentCommand;
import com.github.hotechbackend.payments.domain.model.commands.DeletePaymentCommand;
import com.github.hotechbackend.payments.domain.model.commands.UpdatePaymentCommand;
import com.github.hotechbackend.payments.domain.model.valueobjects.PaymentMethod;
import com.github.hotechbackend.payments.domain.services.PaymentCommandService;
import com.github.hotechbackend.payments.infrastructure.persistence.jpa.repositories.PaymentRepository;

import java.util.Optional;

public class PaymentCommandServiceImpl implements PaymentCommandService {
    private final PaymentRepository paymentRepository;

    public PaymentCommandServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Optional<Payment> handle(CreatePaymentCommand command) {
        var paymentMethod = new PaymentMethod(command.paymentMethod());
        if(paymentRepository.existsByPaymentMethod(paymentMethod))
            throw new IllegalArgumentException("Payment method " + command.paymentMethod() + " already exists");
        var payment = new Payment(command);
        paymentRepository.save(payment);
        return Optional.of(payment);
    }

    @Override
    public Optional<Payment> handle(UpdatePaymentCommand command) {
        var result = paymentRepository.findById(command.id());
        if (result.isEmpty()) throw new IllegalArgumentException("Payment does not exist");
        var paymentToUpdate = result.get();
        try {
            var updatedPayment = paymentRepository.save(paymentToUpdate.updateInformation(command.userId(), command.amount(), command.currency(),
                    command.paymentMethod(), command.status(), command.payment_date()));
            return Optional.of(updatedPayment);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating Payment: " + e.getMessage());
        }
    }

    @Override
    public void handle(DeletePaymentCommand command) {
        if (!paymentRepository.existsById(command.id())) {
            throw new IllegalArgumentException("Payment does not exist");
        }
        try {
            paymentRepository.deleteById(command.id());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting payment: " + e.getMessage());
        }
    }
}
