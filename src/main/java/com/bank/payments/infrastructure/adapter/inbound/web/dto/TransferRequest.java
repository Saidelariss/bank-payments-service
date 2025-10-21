package com.bank.payments.infrastructure.adapter.inbound.web.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record TransferRequest(UUID debtorAccountId, UUID creditorAccountId, BigDecimal amount, String currency) {
}
