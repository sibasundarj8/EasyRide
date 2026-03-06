package com.sibasundarj8.project.easyride.easyrideApp.strategy.impl;

import com.sibasundarj8.project.easyride.easyrideApp.entity.Driver;
import com.sibasundarj8.project.easyride.easyrideApp.entity.RideRequest;
import com.sibasundarj8.project.easyride.easyrideApp.repository.DriverRepository;
import com.sibasundarj8.project.easyride.easyrideApp.strategy.IDriverMatchingStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DriverMatchingHighestRatedIDriverStrategy implements IDriverMatchingStrategy {

    private final DriverRepository driverRepository;

    @Override
    public List<Driver> findMatchingDrivers(RideRequest rideRequest) {
        return driverRepository.findTenNearByTopRatedDrivers(rideRequest.getPickupLocation());
    }
}