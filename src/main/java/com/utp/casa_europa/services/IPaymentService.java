package com.utp.casa_europa.services;

import com.utp.casa_europa.dtos.payment.CardPaymentDto;
import com.utp.casa_europa.dtos.payment.ResponsePaymentDto;

public interface IPaymentService {
    ResponsePaymentDto proccessPayment(CardPaymentDto request);
}
