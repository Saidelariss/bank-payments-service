package com.bank.payments.application.inbound;

import com.bank.payments.domain.model.Payment;
import com.bank.payments.domain.model.PaymentId;

import java.util.Optional;

public interface GetPaymentUseCase {
    Optional<Payment> get(PaymentId paymentId);
}
