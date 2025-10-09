package com.bank.payments.application.outbound;

import com.bank.payments.domain.model.Payment;

public interface SavePayementPort {
    Payment save(Payment payment, String idempotencyKey);
}
