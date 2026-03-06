package com.sibasundarj8.project.easyride.easyrideApp.strategy.impl;

import com.sibasundarj8.project.easyride.easyrideApp.entity.RideRequest;
import com.sibasundarj8.project.easyride.easyrideApp.service.IDistanceService;
import com.sibasundarj8.project.easyride.easyrideApp.strategy.IRideFareCalculationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RideFareDefaultFareCalculationStrategy implements IRideFareCalculationStrategy {

    private final IDistanceService IDistanceService;

    @Override
    public double calculateFare(RideRequest rideRequest) {

        long start = System.currentTimeMillis();
        double distance = IDistanceService.calculateDistance(rideRequest.getPickupLocation(), rideRequest.getDropOffLocation());
        System.out.println("web_api_call timing: " + (System.currentTimeMillis() - start));

        return distance * RIDE_FARE_MULTIPLIER;
    }
}