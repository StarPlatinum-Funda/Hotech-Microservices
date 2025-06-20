package com.github.hotechbackend.payments.domain.services;

import com.github.hotechbackend.payments.domain.model.aggregates.Payment;
import com.github.hotechbackend.payments.domain.model.queries.GetAllPaymentsQuery;
import com.github.hotechbackend.payments.domain.model.queries.GetPaymentByIdQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface PaymentQueryService {
    Optional<Payment> handle(GetPaymentByIdQuery query);
    List<Payment> handle(GetAllPaymentsQuery query);
}
