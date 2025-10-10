package com.bank.payments.application.service;

import com.bank.payments.application.inbound.GetPaymentUseCase;
import com.bank.payments.application.outbound.LoadPaymentPort;
import com.bank.payments.domain.model.Payment;
import com.bank.payments.domain.model.PaymentId;

import java.util.Optional;

public class GetPaymentService implements GetPaymentUseCase {
    private final LoadPaymentPort loadPaymentPort;

    public GetPaymentService(LoadPaymentPort loadPaymentPort) {
        this.loadPaymentPort = loadPaymentPort;
    }

    @Override
    public Optional<Payment> get(PaymentId paymentId) {
        return loadPaymentPort.loadById(paymentId);
    }
}
