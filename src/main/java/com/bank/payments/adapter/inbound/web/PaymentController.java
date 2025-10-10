package com.bank.payments.adapter.inbound.web;

import com.bank.payments.adapter.inbound.web.dto.PaymentResponse;
import com.bank.payments.adapter.inbound.web.dto.TransferRequest;
import com.bank.payments.application.inbound.CreateTransferUseCase;
import com.bank.payments.application.inbound.GetPaymentUseCase;
import com.bank.payments.domain.model.AccountId;
import com.bank.payments.domain.model.Money;
import com.bank.payments.domain.model.PaymentId;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("payments")
@AllArgsConstructor
public class PaymentController {
    private final CreateTransferUseCase createUC;
    private final GetPaymentUseCase getUC;

    @PostMapping("/transfers")
    public ResponseEntity<PaymentResponse> transfer(
            @RequestHeader("Idempotency-key") String key,
            @RequestBody TransferRequest request
    ) {
        var paymentId = createUC.create(key,
                new AccountId(request.debtorAccountId()),
                new AccountId(request.creditorAccountId()),
                new Money(request.amount(), request.currency()));

        return ResponseEntity.accepted().body(new PaymentResponse(paymentId.value(), "FINALIZED", null));
    }

    @GetMapping("/{id}")
    ResponseEntity<PaymentResponse> get(@PathVariable UUID id) {
        return getUC.get(new PaymentId(id))
                .map(payment -> ResponseEntity.ok(new PaymentResponse(payment.id().value(), payment.status().name(), payment.createdAt())))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
