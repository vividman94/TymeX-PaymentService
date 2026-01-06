package com.assignment.payment.service;

import com.assignment.payment.exception.PaymentException;
import com.assignment.payment.model.request.PaymentRequest;
import com.assignment.payment.model.response.PaymentResponse;
import com.assignment.payment.repository.IdempotencyRepository;
import com.assignment.payment.repository.entity.IdempotencyKeyRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final IdempotencyRepository repository;

    @Transactional
    public String processPayment(String idempotencyKey,PaymentRequest request){
        IdempotencyKeyRecord record = null;
        try {
            Optional<IdempotencyKeyRecord> existInDbRecord = repository.findById(idempotencyKey);

            if (existInDbRecord.isPresent()) {
                return existInDbRecord.get().getResponseBody();
            }

            record = createNewRecord(idempotencyKey, request);
            try {
                repository.save(record);
            } catch (DataIntegrityViolationException ex) {
                return repository.findById(idempotencyKey)
                        .get()
                        .getResponseBody();
            }

            String response = executePayment(request);
            record.setStatus("COMPLETED");
            record.setResponseBody(response);
            repository.save(record);
            return record.getResponseBody();
        }
        catch (Exception ex){
            throw new PaymentException(ex.getMessage());
        }
    }

    private String executePayment(PaymentRequest request){
        // connect with payment gateway to prcoess payment and return status
        return generatePaymentId();
    }

    private String generatePaymentId() {
        return UUID.randomUUID().toString();
    }

    private IdempotencyKeyRecord createNewRecord(String idempotencyKey, PaymentRequest request) {
        return IdempotencyKeyRecord.builder()
                .idempotency_key(idempotencyKey)
                .requestHash(request.toString())
                .status("PROCESSING")
                .expires_at(LocalDateTime.now().plusHours(24))
                .build();
    }
}
