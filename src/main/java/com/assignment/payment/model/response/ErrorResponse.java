package com.assignment.payment.model.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ErrorResponse {

    private int error;
    private String message;
    private LocalDateTime timestamp;
}
