package com.utp.casa_europa.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.utp.casa_europa.dtos.payment.CardPaymentDto;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {


    @PostMapping("/proccess_payment")
    public ResponseEntity<?> proccessPayment(@RequestBody CardPaymentDto cardPayment){
        
        return ResponseEntity.ok();
    } 
}
