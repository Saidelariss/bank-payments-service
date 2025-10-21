package com.bank.payments.infrastructure.adapter.outbound.accounts;

import java.math.BigDecimal;
import java.util.UUID;

public interface AccountsClient {
    void debit(UUID accountId, BigDecimal amount);
    void credit(UUID accountId, BigDecimal amount);
}
