package com.utp.casa_europa.dtos;

import com.utp.casa_europa.dtos.payment.CardPaymentDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VentaPagoDto {
    private VentaRequest venta;
    private CardPaymentDto pago;
}
