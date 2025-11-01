package com.sibasundarj8.project.easyride.easyrideApp.strategies.impl;

import com.sibasundarj8.project.easyride.easyrideApp.dtos.RideRequestDto;
import com.sibasundarj8.project.easyride.easyrideApp.entities.Driver;
import com.sibasundarj8.project.easyride.easyrideApp.strategies.DriverMatchingStrategy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverMatchingHighestRatedDriverStrategy implements DriverMatchingStrategy {

    @Override
    public List<Driver> findMatchingDriver(RideRequestDto rideRequestDto) {
        return List.of();
    }
}