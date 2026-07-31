package com.sibasundarj8.project.easyride.easyrideApp.service.impl;

import com.sibasundarj8.project.easyride.easyrideApp.entity.Payment;
import com.sibasundarj8.project.easyride.easyrideApp.entity.Ride;
import com.sibasundarj8.project.easyride.easyrideApp.entity.enums.PaymentStatus;
import com.sibasundarj8.project.easyride.easyrideApp.exception.ResourceNotFoundException;
import com.sibasundarj8.project.easyride.easyrideApp.repository.PaymentRepository;
import com.sibasundarj8.project.easyride.easyrideApp.service.IPaymentService;
import com.sibasundarj8.project.easyride.easyrideApp.strategy.PaymentStrategyManager;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PaymentServiceImpl implements IPaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentStrategyManager paymentStrategyManager;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public void processPayment(Ride ride) {
        Payment payment = paymentRepository.findByRide(ride)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found for ride with rideId " + ride.getId()));

        paymentStrategyManager.getPaymentStrategy(ride.getPaymentMethod()).processPayment(payment);
    }

    @Override
    @Transactional
    public Payment createPayment(Ride ride) {
        Payment payment = Payment.builder()
                .paymentMethod(ride.getPaymentMethod())
                .ride(ride)
                .amount(ride.getFare())
                .paymentStatus(PaymentStatus.PENDING)
                .build();

        return paymentRepository.save(payment);
    }

    @Override
    @Transactional
    public void updatePayment(Payment payment, PaymentStatus status) {
        payment.setPaymentStatus(status);
        paymentRepository.save(payment);
    }
}