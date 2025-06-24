package com.utp.casa_europa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.utp.casa_europa.dtos.payment.CardPaymentDto;
import com.utp.casa_europa.dtos.payment.ResponsePaymentDto;
import com.utp.casa_europa.services.IPaymentService;
import com.utp.casa_europa.utils.Response;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    private IPaymentService paymentService;


    @PostMapping("/proccess_payment")
    public ResponseEntity<Response<ResponsePaymentDto>> proccessPayment(@RequestBody CardPaymentDto cardPayment){
        ResponsePaymentDto response = paymentService.proccessPayment(cardPayment);
        return Response.setResponse(response, HttpStatus.OK);
    } 
}
