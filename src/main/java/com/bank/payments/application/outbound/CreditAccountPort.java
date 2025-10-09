package com.bank.payments.application.outbound;

import com.bank.payments.domain.model.AccountId;
import com.bank.payments.domain.model.Money;

public interface CreditAccountPort {
    void credit(AccountId accountId, Money money);
}
