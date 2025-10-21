package com.bank.payments.infrastructure.adapter.inbound.web.dto;

import java.time.Instant;
import java.util.UUID;

public record PaymentResponse(UUID paymentId, String status, Instant createdAt) {
}
