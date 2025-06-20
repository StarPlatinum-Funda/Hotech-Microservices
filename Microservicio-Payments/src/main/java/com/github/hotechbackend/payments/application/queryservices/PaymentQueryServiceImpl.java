package com.github.hotechbackend.payments.application.queryservices;

import com.github.hotechbackend.payments.domain.model.aggregates.Payment;
import com.github.hotechbackend.payments.domain.model.queries.GetAllPaymentsQuery;
import com.github.hotechbackend.payments.domain.model.queries.GetPaymentByIdQuery;
import com.github.hotechbackend.payments.domain.services.PaymentQueryService;
import com.github.hotechbackend.payments.infrastructure.persistence.jpa.repositories.PaymentRepository;

import java.util.List;
import java.util.Optional;

public class PaymentQueryServiceImpl implements PaymentQueryService {
    private final PaymentRepository paymentRepository;

    public PaymentQueryServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Optional<Payment> handle(GetPaymentByIdQuery query) {
        return paymentRepository.findById(query.id());
    }

    @Override
    public List<Payment> handle(GetAllPaymentsQuery query) {
        return paymentRepository.findAll();
    }
}
