package com.bank.payments.application.service;

import com.bank.payments.application.inbound.CreateTransferUseCase;
import com.bank.payments.application.outbound.CreditAccountPort;
import com.bank.payments.application.outbound.DebitAccountPort;
import com.bank.payments.application.outbound.LoadPaymentByIdempotencyPort;
import com.bank.payments.application.outbound.SavePayementPort;
import com.bank.payments.domain.model.*;

import java.time.Instant;
import java.util.Optional;

public class CreateTransferService implements CreateTransferUseCase {
    private final CreditAccountPort creditAccountPort;
    private final DebitAccountPort debitAccountPort;
    private final LoadPaymentByIdempotencyPort loadPaymentByIdempotencyPort;
    private final SavePayementPort savePayementPort;

    public CreateTransferService(CreditAccountPort creditAccountPort, DebitAccountPort debitAccountPort, LoadPaymentByIdempotencyPort loadPaymentByIdempotencyPort, SavePayementPort savePayementPort) {
        this.creditAccountPort = creditAccountPort;
        this.debitAccountPort = debitAccountPort;
        this.loadPaymentByIdempotencyPort = loadPaymentByIdempotencyPort;
        this.savePayementPort = savePayementPort;
    }

    @Override
    public PaymentId create(String idempotencyKey, AccountId debtor, AccountId creditor, Money amount) {
        Optional<Payment> existingPayment = loadPaymentByIdempotencyPort.loadByIdempotencyKey(idempotencyKey);
        if (existingPayment.isPresent()) return existingPayment.get().id();

        debitAccountPort.debit(debtor, amount);
        try {
            creditAccountPort.credit(creditor, amount);
        } catch (RuntimeException e) {
            creditAccountPort.credit(debtor, amount);
            throw e;
        }

        Payment payment = new Payment(PaymentId.newId(), debtor, creditor, amount, PaymentStatus.FINALIZED, Instant.now());
        return savePayementPort.save(payment, idempotencyKey).id();

    }
}
