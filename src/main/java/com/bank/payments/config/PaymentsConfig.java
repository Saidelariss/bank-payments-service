package com.bank.payments.config;

import com.bank.payments.application.inbound.CreateTransferUseCase;
import com.bank.payments.application.inbound.GetPaymentUseCase;
import com.bank.payments.application.outbound.*;
import com.bank.payments.application.service.CreateTransferService;
import com.bank.payments.application.service.GetPaymentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaymentsConfig {
    @Bean
    CreateTransferUseCase createTransferUseCase(
            LoadPaymentByIdempotencyPort loadByIdem,
            SavePayementPort savePayment,
            DebitAccountPort debit,
            CreditAccountPort credit) {
        return new CreateTransferService(credit, debit, loadByIdem, savePayment);
    }

    @Bean
    GetPaymentUseCase getPaymentUseCase(LoadPaymentPort loader) {
        return new GetPaymentService(loader);
    }

}
