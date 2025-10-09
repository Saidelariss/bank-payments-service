package com.bank.payments.domain.model;

import java.time.Instant;

public class Payment {
    private final PaymentId id;
    private final AccountId debtor;
    private final AccountId creditor;
    private final Money amount;
    private final PaymentStatus status;
    private final Instant createdAt;

    public Payment(PaymentId id, AccountId debtor, AccountId creditor, Money amount, PaymentStatus status, Instant createdAt){
        this.id=id; this.debtor=debtor; this.creditor=creditor; this.amount=amount; this.status=status; this.createdAt = createdAt==null?Instant.now():createdAt;
        if (debtor.value().equals(creditor.value())) throw new IllegalArgumentException("same account");
    }
    public PaymentId id(){return id;} public AccountId debtor(){return debtor;}
    public AccountId creditor(){return creditor;} public Money amount(){return amount;}
    public PaymentStatus status(){return status;} public Instant createdAt(){return createdAt;}

}
