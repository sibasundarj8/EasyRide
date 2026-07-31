package com.sibasundarj8.project.easyride.easyrideApp.strategy;

import com.sibasundarj8.project.easyride.easyrideApp.entity.Payment;

public interface IPaymentStrategy {
    double PLATFORM_COMMISSION = 0.3;
    void processPayment(Payment payment);
}