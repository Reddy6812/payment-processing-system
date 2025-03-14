package com.example.payments.dto;

import lombok.Data;

@Data
public class PaymentRequest {
    private String payer;
    private Double amount;
}
