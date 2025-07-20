package com.utp.casa_europa.exceptions;

public class PaymentNotApprovedException extends RuntimeException {
    public PaymentNotApprovedException() {
        super("Pago no aprovado");
    }
}
