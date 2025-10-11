package com.bank.payments.adapter.outbound.accounts;

import com.bank.payments.application.outbound.CreditAccountPort;
import com.bank.payments.application.outbound.DebitAccountPort;
import com.bank.payments.domain.model.AccountId;
import com.bank.payments.domain.model.Money;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

@Component
public class HttpAccountsClient implements AccountsClient, CreditAccountPort, DebitAccountPort {

    @Override
    public void debit(UUID accountId, BigDecimal amount) {
        //TODO
        //faire appel au service accounts
    }

    @Override
    public void credit(UUID accountId, BigDecimal amount) {
        //TODO
        //faire appel au service accounts
    }

    @Override
    public void credit(AccountId accountId, Money money) {
        credit(accountId.value(), money.value());
    }

    @Override
    public void debit(AccountId accountId, Money money) {
        debit(accountId.value(), money.value());
    }
}
