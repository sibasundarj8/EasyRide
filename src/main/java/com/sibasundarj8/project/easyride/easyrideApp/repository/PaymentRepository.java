package com.sibasundarj8.project.easyride.easyrideApp.repository;

import com.sibasundarj8.project.easyride.easyrideApp.entity.Payment;
import com.sibasundarj8.project.easyride.easyrideApp.entity.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Optional<Payment> findByRide(Ride ride);
}