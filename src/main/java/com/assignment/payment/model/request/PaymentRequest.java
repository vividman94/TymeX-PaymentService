package com.assignment.payment.model.request;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PaymentRequest {

    public String fromAccount;
    public String toAccount;
    public double amount;
}
