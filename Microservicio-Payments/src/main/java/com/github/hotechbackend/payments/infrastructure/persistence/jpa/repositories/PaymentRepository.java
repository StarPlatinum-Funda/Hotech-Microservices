package com.github.hotechbackend.payments.infrastructure.persistence.jpa.repositories;

import com.github.hotechbackend.payments.domain.model.aggregates.Payment;
import com.github.hotechbackend.payments.domain.model.valueobjects.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findByPaymentMethod(PaymentMethod paymentMethod);
    boolean existsByPaymentMethod(PaymentMethod paymentMethod);
}
