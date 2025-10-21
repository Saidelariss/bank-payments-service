package com.bank.payments.infrastructure.adapter.outbound.accounts;

import com.bank.payments.infrastructure.adapter.outbound.accounts.dto.AccountOperationRequest;
import com.bank.payments.application.outbound.CreditAccountPort;
import com.bank.payments.application.outbound.DebitAccountPort;
import com.bank.payments.domain.model.AccountId;
import com.bank.payments.domain.model.Money;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.UUID;

@Component
@AllArgsConstructor
public class HttpAccountsClient implements AccountsClient, CreditAccountPort, DebitAccountPort {
    private final RestTemplate restTemplate;

    @Override
    public void debit(UUID accountId, BigDecimal amount) {
        HttpEntity<AccountOperationRequest> request = new HttpEntity<>(new AccountOperationRequest(amount));
        restTemplate.postForEntity("http://localhost:8082/accounts/" + accountId.toString() + "/debit", request, Void.class);
    }

    @Override
    public void credit(UUID accountId, BigDecimal amount) {
        HttpEntity<AccountOperationRequest> request = new HttpEntity<>(new AccountOperationRequest(amount));
        restTemplate.postForEntity("http://localhost:8082/accounts/" + accountId.toString() + "/credit", request, Void.class);
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
