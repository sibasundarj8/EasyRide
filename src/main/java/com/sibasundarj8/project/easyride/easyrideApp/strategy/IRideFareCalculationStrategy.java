package com.sibasundarj8.project.easyride.easyrideApp.strategy;

import com.sibasundarj8.project.easyride.easyrideApp.entity.RideRequest;

public interface IRideFareCalculationStrategy {
    double RIDE_FARE_MULTIPLIER = 10;
    double calculateFare(RideRequest rideRequest);
}