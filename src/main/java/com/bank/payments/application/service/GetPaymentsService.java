package com.bank.payments.application.service;

import com.bank.payments.application.inbound.GetPaymentsUseCase;
import com.bank.payments.application.outbound.LoadPaymentsPort;
import com.bank.payments.domain.model.Payment;

import java.util.List;

public class GetPaymentsService implements GetPaymentsUseCase {
    public GetPaymentsService(LoadPaymentsPort loadPaymentsPort) {
        this.loadPaymentsPort = loadPaymentsPort;
    }

    private final LoadPaymentsPort loadPaymentsPort;
    @Override
    public List<Payment> getAll() {
        return loadPaymentsPort.loadAll();
    }
}
