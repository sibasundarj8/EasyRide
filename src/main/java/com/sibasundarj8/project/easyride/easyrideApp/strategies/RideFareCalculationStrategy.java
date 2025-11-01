package com.sibasundarj8.project.easyride.easyrideApp.strategies;

import com.sibasundarj8.project.easyride.easyrideApp.dtos.RideRequestDto;

public interface RideFareCalculationStrategy {
    double calculateFare(RideRequestDto rideRequestDto);
}