package com.assignment.payment.exception;

import com.assignment.payment.model.response.PaymentResponse;

public class PaymentException extends RuntimeException{

    private String message;
    public PaymentException(String message){
        super(message);
        this.message=message;
    }

    public PaymentException(String message,Throwable cause){
        super(message,cause);
        this.message=message;
    }
}
