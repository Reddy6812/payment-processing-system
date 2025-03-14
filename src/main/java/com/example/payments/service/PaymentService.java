package com.example.payments.service;

import com.example.payments.dto.PaymentRequest;
import com.example.payments.model.Payment;
import com.example.payments.repository.PaymentRepository;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${spring.kafka.template.default-topic:payment-transactions}")
    private String paymentTopic;

    public PaymentService(PaymentRepository paymentRepository,
                          KafkaTemplate<String, String> kafkaTemplate) {
        this.paymentRepository = paymentRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    public Payment createPayment(PaymentRequest request) {
        // Create a new payment record
        Payment payment = new Payment();
        payment.setTransactionId(UUID.randomUUID().toString());
        payment.setPayer(request.getPayer());
        payment.setAmount(request.getAmount());
        payment.setStatus("PENDING");

        Payment savedPayment = paymentRepository.save(payment);

        // Publish the payment event to Kafka
        ProducerRecord<String, String> record = new ProducerRecord<>(
                paymentTopic,
                savedPayment.getTransactionId(),
                "Payment Created"
        );
        kafkaTemplate.send(record);

        return savedPayment;
    }
}
