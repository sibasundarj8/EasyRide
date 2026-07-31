package com.sibasundarj8.project.easyride.easyrideApp.strategy.impl;

import com.sibasundarj8.project.easyride.easyrideApp.entity.Driver;
import com.sibasundarj8.project.easyride.easyrideApp.entity.Payment;
import com.sibasundarj8.project.easyride.easyrideApp.entity.enums.PaymentStatus;
import com.sibasundarj8.project.easyride.easyrideApp.entity.enums.TransactionMethod;
import com.sibasundarj8.project.easyride.easyrideApp.repository.PaymentRepository;
import com.sibasundarj8.project.easyride.easyrideApp.service.IWalletService;
import com.sibasundarj8.project.easyride.easyrideApp.strategy.IPaymentStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CashPaymentStrategy implements IPaymentStrategy {

    private final PaymentRepository paymentRepository;
    private final IWalletService walletService;

    @Override
    @Transactional
    public void processPayment(Payment payment) {
        Driver driver = payment.getRide().getDriver();
        double platformCommission = payment.getAmount() * PLATFORM_COMMISSION;

        walletService.deductMoneyFromWallet(driver.getUser(), platformCommission, TransactionMethod.RIDE, payment.getRide(), null);

        payment.setPaymentStatus(PaymentStatus.CONFIRMED);
        paymentRepository.save(payment);
    }
}