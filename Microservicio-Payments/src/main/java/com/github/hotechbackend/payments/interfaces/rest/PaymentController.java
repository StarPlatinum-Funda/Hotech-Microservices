package com.github.hotechbackend.payments.interfaces.rest;

import com.github.hotechbackend.payments.domain.model.aggregates.Payment;
import com.github.hotechbackend.payments.domain.model.commands.DeletePaymentCommand;
import com.github.hotechbackend.payments.domain.model.queries.GetAllPaymentsQuery;
import com.github.hotechbackend.payments.domain.model.queries.GetPaymentByIdQuery;
import com.github.hotechbackend.payments.domain.services.PaymentCommandService;
import com.github.hotechbackend.payments.domain.services.PaymentQueryService;
import com.github.hotechbackend.payments.interfaces.rest.resources.CreatePaymentResource;
import com.github.hotechbackend.payments.interfaces.rest.resources.PaymentResource;
import com.github.hotechbackend.payments.interfaces.rest.resources.UpdatePaymentResource;
import com.github.hotechbackend.payments.interfaces.rest.transform.CreatePaymentCommandFromResourceAssembler;
import com.github.hotechbackend.payments.interfaces.rest.transform.PaymentResourceFromEntityAssembler;
import com.github.hotechbackend.payments.interfaces.rest.transform.UpdatePaymentCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(value = "api/v1/payment", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Payments", description = "Payment Management Endpoints")
public class PaymentController {

    private final PaymentCommandService paymentCommandService;
    private final PaymentQueryService paymentQueryService;

    public PaymentController(PaymentCommandService paymentCommandService, PaymentQueryService paymentQueryService) {
        this.paymentCommandService = paymentCommandService;
        this.paymentQueryService = paymentQueryService;
    }

    @PostMapping
    public ResponseEntity<PaymentResource> createItem(
            @RequestBody CreatePaymentResource createPaymentResource) {
        Optional<Payment> PaymentSource = paymentCommandService
                .handle(CreatePaymentCommandFromResourceAssembler.toCommandFromResource(createPaymentResource));
        return PaymentSource.map(source ->
                        new ResponseEntity<>(PaymentResourceFromEntityAssembler
                                .toResourceFromEntity(source), CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping
    public ResponseEntity<List<PaymentResource>> getAllPayments() {
        List<Payment> paymentSource = paymentQueryService.handle(new GetAllPaymentsQuery());
        var paymentResource = paymentSource
                .stream().map(PaymentResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(paymentResource);
    }

    @GetMapping("/{paymentId}")
    public ResponseEntity<PaymentResource> getIPaymentById(@PathVariable Long paymentId) {
        var GetPaymentsByIdQuery = new GetPaymentByIdQuery(paymentId);
        var Payment = paymentQueryService.handle(GetPaymentsByIdQuery);
        if (Payment.isEmpty()) return ResponseEntity.notFound().build();
        var paymentResource = PaymentResourceFromEntityAssembler.toResourceFromEntity(Payment.get());
        return ResponseEntity.ok(paymentResource);
    }

    @PutMapping("/{paymentId}")
    public ResponseEntity<PaymentResource> updateInventory(@PathVariable Long paymentId, @RequestBody UpdatePaymentResource updatePaymentResource) {
        var UpdatePaymentCommand = UpdatePaymentCommandFromResourceAssembler.toCommandFromResource(paymentId, updatePaymentResource);
        var updatedPayment = paymentCommandService.handle(UpdatePaymentCommand);
        if (updatedPayment.isEmpty()) return ResponseEntity.notFound().build();

        var paymentResource = PaymentResourceFromEntityAssembler.toResourceFromEntity(updatedPayment.get());
        return ResponseEntity.ok(paymentResource);
    }

    @DeleteMapping("/{paymentId}")
    public ResponseEntity<?> deleteInventory(@PathVariable Long paymentId) {
        var deletePaymentCommand = new DeletePaymentCommand(paymentId);
        paymentCommandService.handle(deletePaymentCommand);
        return ResponseEntity.ok("Payment with id " + paymentId + " has been deleted.");
    }

}
