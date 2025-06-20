package com.github.hotechbackend.payments.interfaces.rest.transform;

import com.github.hotechbackend.payments.domain.model.commands.UpdatePaymentCommand;
import com.github.hotechbackend.payments.interfaces.rest.resources.UpdatePaymentResource;

public class UpdatePaymentCommandFromResourceAssembler {
    public static UpdatePaymentCommand toCommandFromResource(Long paymentId, UpdatePaymentResource resource) {
        return new UpdatePaymentCommand(
                paymentId, resource.userId(), resource.amount(),
                resource.currency(), resource.paymentMethod(), resource.status(),
                resource.payment_date());
    }
}
