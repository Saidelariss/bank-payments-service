package com.bank.payments.application.outbound;

import com.bank.payments.domain.model.Payment;

import java.util.Optional;

public interface LoadPaymentByIdempotencyPort {
    Optional<Payment> loadByIdempotencyKey(String key);
}
