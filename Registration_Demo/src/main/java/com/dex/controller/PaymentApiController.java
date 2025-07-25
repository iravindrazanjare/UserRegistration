package com.dex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dex.models.ResponseMessage;
import com.dex.service.PaymentService;

@RestController
@RequestMapping({ "/pay" })
public class PaymentApiController
{
    @Autowired
    private PaymentService paymentService;
    
    @PostMapping({ "/generatePayUrl" })
    public ResponseEntity<ResponseMessage> generatePaymentUrl() {
        String message = this.paymentService.generatePaymentUrl();
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
    }
}