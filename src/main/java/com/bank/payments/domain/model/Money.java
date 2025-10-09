package com.bank.payments.domain.model;

import java.math.BigDecimal;

public record Money(BigDecimal value, String currency) {
    public Money {
        if (value == null || value.signum() <= 0) throw new IllegalArgumentException("amount>0");
        if (currency == null || currency.length()!=3) throw new IllegalArgumentException("ccy");
    }
}
