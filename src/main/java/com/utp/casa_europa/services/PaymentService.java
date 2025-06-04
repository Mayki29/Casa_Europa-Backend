package com.utp.casa_europa.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.mercadopago.client.common.IdentificationRequest;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.client.payment.PaymentCreateRequest;
import com.mercadopago.client.payment.PaymentPayerRequest;
import com.mercadopago.core.MPRequestOptions;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.payment.Payment;
import com.utp.casa_europa.dtos.payment.CardPaymentDto;
import com.utp.casa_europa.dtos.payment.ResponsePaymentDto;

@Service
public class PaymentService implements IPaymentService {

    @Value("${PAYMENT_ACCESS_TOKEN}")
    private String ACCESS_TOKEN;

    @Override
    public ResponsePaymentDto proccessPayment(CardPaymentDto request) {
        try{
            Map<String, String> customHeaders = new HashMap<>();
            customHeaders.put("x-idempotency-key","");
            MPRequestOptions requestOptions = MPRequestOptions.builder()
                                            .customHeaders(customHeaders)
                                            .build();
            PaymentClient client = new PaymentClient();
            
            PaymentCreateRequest paymentCreateRequest = PaymentCreateRequest.builder()
                    .transactionAmount(request.getTransactionAmount())
                    .token(request.getToken())
                    .description(request.getDescription())
                    .installments(request.getInstallments())
                    .paymentMethodId(request.getPaymentMethodId())
                    .payer(PaymentPayerRequest.builder()
                        .email(request.getPayer().getEmail())
                        .identification(IdentificationRequest.builder()
                            .type(request.getPayer().getIdentification().getType())
                            .number(request.getPayer().getIdentification().getNumber())
                            .build()
                        ).build()
                    ).build();
    
            Payment createdPayment = client.create(paymentCreateRequest);
            return ResponsePaymentDto.builder()
                .id(createdPayment.getId())
                .status(createdPayment.getStatus())
                .statusDetail(createdPayment.getStatusDetail())
                .build();

        }catch(MPApiException apiException){
            System.out.println(apiException.getApiResponse().getContent());
            throw new InternalError();
        }catch(MPException exception){
            System.out.println(exception.getMessage());
            throw new InternalError();
        }
                

    }

}
