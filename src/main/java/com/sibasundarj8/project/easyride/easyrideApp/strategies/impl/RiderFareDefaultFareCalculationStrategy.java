package com.sibasundarj8.project.easyride.easyrideApp.strategies.impl;

import com.sibasundarj8.project.easyride.easyrideApp.dtos.RideRequestDto;
import com.sibasundarj8.project.easyride.easyrideApp.strategies.RideFareCalculationStrategy;
import org.springframework.stereotype.Service;

@Service
public class RiderFareDefaultFareCalculationStrategy implements RideFareCalculationStrategy {

    @Override
    public double calculateFare(RideRequestDto rideRequestDto) {
        return 0;
    }
}
