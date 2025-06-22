package com.github.hotechbackend.payments.infrastructure.listeners;

import com.github.hotechbackend.payments.infrastructure.persistence.jpa.repositories.PaymentRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PaymentsListener {

    private final PaymentRepository paymentRepository;

    public PaymentsListener(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    private Map<String, Object> item;

    /*
    {

    }
    */

    @KafkaListener(topics = "payment-topic", groupId = "payment", containerFactory = "kafkaListenerContainerFactory")
    public void reciveItem(Map<String, Object> val){
        item = val;
        var id = (Number)item.get("id");
        System.out.println(item.toString());
    }
}
