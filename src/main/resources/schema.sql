CREATE TABLE idempotency_keys (
    idempotency_key VARCHAR(100) PRIMARY KEY,
    request_hash VARCHAR(64),
    response_body TEXT,
    status VARCHAR(20),
    created_at TIMESTAMP,
    expires_at TIMESTAMP
);