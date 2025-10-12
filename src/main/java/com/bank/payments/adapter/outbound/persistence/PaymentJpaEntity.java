package com.bank.payments.adapter.outbound.persistence;

import com.bank.payments.domain.model.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Setter
@Getter
@Table(name = "payments")
public class PaymentJpaEntity {
    @Id
    private UUID id;
    private UUID debtorAccountId;
    private UUID creditorAccountId;
    private BigDecimal amount;
    private String currency;
    private String status;
    private String idempotencyKey;
    private Instant createdAt = Instant.now();

    public static PaymentJpaEntity fromDomain(Payment payment) {
        PaymentJpaEntity paymentJpaEntity = new PaymentJpaEntity();
        paymentJpaEntity.setId(payment.id().value());
        paymentJpaEntity.setDebtorAccountId(payment.debtor().value());
        paymentJpaEntity.setCreditorAccountId(payment.creditor().value());
        paymentJpaEntity.setAmount(payment.amount().value());
        paymentJpaEntity.setCurrency(payment.amount().currency());
        paymentJpaEntity.setStatus(payment.status().name());
        paymentJpaEntity.setCreatedAt(payment.createdAt());

        return paymentJpaEntity;
    }

    public Payment toDomain() {
        return new Payment(new PaymentId(id),
                new AccountId(debtorAccountId),
                new AccountId(creditorAccountId),
                new Money(amount, currency),
                PaymentStatus.valueOf(status),
                createdAt);
    }
}
