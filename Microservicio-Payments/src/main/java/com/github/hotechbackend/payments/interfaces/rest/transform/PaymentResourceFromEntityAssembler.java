package com.github.hotechbackend.payments.interfaces.rest.transform;

import com.github.hotechbackend.payments.domain.model.aggregates.Payment;
import com.github.hotechbackend.payments.interfaces.rest.resources.PaymentResource;

public class PaymentResourceFromEntityAssembler {
    public static PaymentResource toResourceFromEntity(Payment entity) {
        return new PaymentResource(entity.getId(), entity.getUserId(), entity.getAmount(), entity.getCurrency(),
                entity.getPaymentMethod().paymentMethod(), entity.getStatus().status(), entity.getPayment_date());

    }
}
