package com.github.hotechbackend.payments.interfaces.rest.transform;

import com.github.hotechbackend.payments.domain.model.commands.CreatePaymentCommand;
import com.github.hotechbackend.payments.interfaces.rest.resources.CreatePaymentResource;

public class CreatePaymentCommandFromResourceAssembler {
    public static CreatePaymentCommand toCommandFromResource(CreatePaymentResource resource) {
        return new CreatePaymentCommand(resource.id(), resource.userId(), resource.amount(), resource.currency(),
                resource.paymentMethod(), resource.status(), resource.payment_date());
    }
}
