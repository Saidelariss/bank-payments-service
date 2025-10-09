package com.bank.payments.application.outbound;

import com.bank.payments.domain.model.Payment;
import com.bank.payments.domain.model.PaymentId;

import java.util.Optional;

public interface LoadPaymentPort {
    Optional<Payment> loadById(PaymentId paymentId);
}
