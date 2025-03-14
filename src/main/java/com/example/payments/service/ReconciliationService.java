package com.example.payments.service;

import com.example.payments.model.Payment;
import com.example.payments.repository.PaymentRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ReconciliationService {

    private final PaymentRepository paymentRepository;

    public ReconciliationService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    // Listen to the "payment-transactions" topic for reconciliation events.
    @KafkaListener(topics = "payment-transactions", groupId = "reconciliation-group")
    public void listenPaymentTransactions(String message, @org.springframework.kafka.support.KafkaHeaders.Key String key) {
        // Here the key is the transactionId, and message is the event description

        // Fetch the payment record by transactionId
        Payment payment = paymentRepository.findByTransactionId(key);
        if (payment != null) {
            // Simulate reconciliation: mark the payment as COMPLETED
            payment.setStatus("COMPLETED");
            paymentRepository.save(payment);

            // Optionally, publish an update event on another topic (not shown here)
        }
    }
}
