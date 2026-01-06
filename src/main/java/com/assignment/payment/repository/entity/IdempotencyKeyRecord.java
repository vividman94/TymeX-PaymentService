package com.assignment.payment.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name="idempotency_keys")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IdempotencyKeyRecord {

    @Id
    private String idempotency_key;
    @Column(name="request_hash")
    private String requestHash;
    @Column(name="response_body")
    private String responseBody;
    @Column(name="status")
    private String status;

    @CreationTimestamp
    private LocalDateTime created_at;
    private LocalDateTime expires_at;
}
