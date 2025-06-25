package com.utp.casa_europa.dtos.payment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PayerDto {
    private String email;
    private PayerIdentificationDto identification;
}
