package com.bank.payments.domain.model;

import java.util.UUID;

public record PaymentId(UUID value) {
    public PaymentId {if(value == null) throw new IllegalArgumentException("id");}
    public static PaymentId newId(){return new PaymentId(UUID.randomUUID());}
}
