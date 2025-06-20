package com.github.hotechbackend.payments.domain.model.aggregates;

import com.github.hotechbackend.payments.domain.model.commands.CreatePaymentCommand;
import com.github.hotechbackend.payments.domain.model.valueobjects.PaymentMethod;
import com.github.hotechbackend.payments.domain.model.valueobjects.Status;
import com.github.hotechbackend.payments.domain.model.valueobjects.UserId;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.util.Date;

@Getter
@Entity
public class Payment extends AbstractAggregateRoot<Payment> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Embedded
    @Getter
    private UserId userId;

    @Getter
    private float amount;

    @Getter
    private float currency;

    @Embedded
    @Getter
    private PaymentMethod paymentMethod;

    @Embedded
    @Getter
    private Status status;

    @Getter
    private Date payment_date;


    public Payment(Long userId, String paymentMethod, String status) {
        this.userId = new UserId(userId);
        this.paymentMethod = new PaymentMethod(paymentMethod);
        this.status = new Status(status);
    }

    public Payment(CreatePaymentCommand command) {
        this.id = command.id();
        this.userId = new UserId(command.userId());
        this.amount = command.amount();
        this.currency = command.currency();
        this.paymentMethod = new PaymentMethod(command.paymentMethod());
        this.status = new Status(command.status());
        this.payment_date = command.payment_date();
    }

    public Payment() { }

    public Long getUserId() { return userId.userId(); }

    public Payment updateInformation(Long userId, float amount, float currency,
                                     String paymentMethod, String status, Date payment_date) {
        this.userId = new UserId(userId);
        this.amount = amount;
        this.currency = currency;
        this.paymentMethod = new PaymentMethod(paymentMethod);
        this.status = new Status(status);
        this.payment_date = payment_date;
        return this;
    }
}
