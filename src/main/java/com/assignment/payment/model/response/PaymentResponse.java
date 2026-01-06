package com.assignment.payment.model.response;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class PaymentResponse {

    private String paymentId;
    private String status;
}
