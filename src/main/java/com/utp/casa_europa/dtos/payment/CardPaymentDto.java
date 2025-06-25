package com.utp.casa_europa.dtos.payment;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CardPaymentDto {
    private String token;
    private String issuerId;
    private String paymentMethodId;
    private BigDecimal transactionAmount;
    private Integer installments;
    private String description;
    private PayerDto payer;

}
