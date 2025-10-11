package com.bank.payments.adapter.outbound.persistence;

import com.bank.payments.application.outbound.LoadPaymentByIdempotencyPort;
import com.bank.payments.application.outbound.LoadPaymentPort;
import com.bank.payments.application.outbound.SavePayementPort;
import com.bank.payments.domain.model.Payment;
import com.bank.payments.domain.model.PaymentId;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class PaymentPersistenceAdapter implements SavePayementPort, LoadPaymentByIdempotencyPort, LoadPaymentPort {
    private final PaymentJpaRepository paymentJpaRepository;

    @Override
    public Optional<Payment> loadByIdempotencyKey(String key) {
        return paymentJpaRepository.findByIdempotencyKey(key).map(PaymentJpaEntity::toDomain);
    }

    @Override
    public Optional<Payment> loadById(PaymentId paymentId) {
        return paymentJpaRepository.findById(paymentId.value()).map(PaymentJpaEntity::toDomain);
    }

    @Override
    public Payment save(Payment payment, String idempotencyKey) {
        PaymentJpaEntity paymentJpaEntity = paymentJpaRepository.save(PaymentJpaEntity.fromDomain(payment));

        return paymentJpaEntity.toDomain();
    }
}
