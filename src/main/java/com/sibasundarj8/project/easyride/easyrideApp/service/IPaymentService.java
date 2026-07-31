package com.sibasundarj8.project.easyride.easyrideApp.service;

import com.sibasundarj8.project.easyride.easyrideApp.entity.Payment;
import com.sibasundarj8.project.easyride.easyrideApp.entity.Ride;
import com.sibasundarj8.project.easyride.easyrideApp.entity.enums.PaymentStatus;

public interface IPaymentService {

    void processPayment(Ride ride);

    Payment createPayment(Ride ride);

    void updatePayment(Payment payment, PaymentStatus status);
}