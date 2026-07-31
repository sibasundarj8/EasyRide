package com.sibasundarj8.project.easyride.easyrideApp.strategy;

import com.sibasundarj8.project.easyride.easyrideApp.entity.enums.PaymentMethod;
import com.sibasundarj8.project.easyride.easyrideApp.strategy.impl.CashPaymentStrategy;
import com.sibasundarj8.project.easyride.easyrideApp.strategy.impl.WalletPaymentStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PaymentStrategyManager {

    private final CashPaymentStrategy cashPaymentStrategy;
    private final WalletPaymentStrategy walletPaymentStrategy;

    public IPaymentStrategy getPaymentStrategy(PaymentMethod paymentMethod) {
        return switch (paymentMethod) {
            case CASH -> cashPaymentStrategy;
            case WALLET -> walletPaymentStrategy;
        };
    }
}