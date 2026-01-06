package com.assignment.payment.controller;

import com.assignment.payment.exception.PaymentException;
import com.assignment.payment.model.request.PaymentRequest;
import com.assignment.payment.model.response.PaymentResponse;
import com.assignment.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping(value = "/pay",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PaymentResponse> processPayment(@RequestHeader(name = "idempotency-key",required = false) String idempotencyKey, @RequestBody PaymentRequest paymentRequest){
        if (idempotencyKey == null || idempotencyKey.isBlank()) {
            throw new PaymentException(
                    "Idempotency-Key header is required");
        }
        String paymentId = paymentService.processPayment(idempotencyKey,paymentRequest);
        PaymentResponse response = PaymentResponse.builder()
                .paymentId(paymentId)
                .status("SUCCESS")
                .build();
        return ResponseEntity.ok(response);
    }
}
