package com.example.payments.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {

    // Create Kafka topics for transactions and status updates
    @Bean
    public NewTopic paymentTransactionsTopic() {
        return new NewTopic("payment-transactions", 3, (short) 1);
    }

    @Bean
    public NewTopic paymentStatusUpdatesTopic() {
        return new NewTopic("payment-status-updates", 3, (short) 1);
    }
}
