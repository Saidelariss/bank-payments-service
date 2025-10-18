package com.bank.payments.application.outbound;

import com.bank.payments.domain.model.Payment;

import java.util.List;

public interface LoadPaymentsPort {
    List<Payment> loadAll();
}
