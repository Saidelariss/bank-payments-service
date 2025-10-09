package com.bank.payments.application.inbound;

import com.bank.payments.domain.model.AccountId;
import com.bank.payments.domain.model.Money;
import com.bank.payments.domain.model.PaymentId;

public interface CreateTransferUseCase {
    PaymentId create(String idempotencyKey, AccountId debtor, AccountId creditor, Money amount);}
