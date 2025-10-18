package com.bank.payments.application.inbound;

import com.bank.payments.domain.model.Payment;

import java.util.List;

public interface GetPaymentsUseCase {
    List<Payment> getAll();
}
