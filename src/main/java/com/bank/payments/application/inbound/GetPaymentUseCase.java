package com.bank.payments.application.inbound;

import com.bank.payments.domain.model.Payment;

import java.util.Optional;

public interface GetPaymentUseCase {
    Optional<Payment> get(String paymentId);
}
