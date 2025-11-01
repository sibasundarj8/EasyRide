package com.sibasundarj8.project.easyride.easyrideApp.strategies;

import com.sibasundarj8.project.easyride.easyrideApp.dtos.RideRequestDto;
import com.sibasundarj8.project.easyride.easyrideApp.entities.Driver;

import java.util.List;

public interface DriverMatchingStrategy {
    List<Driver> findMatchingDriver(RideRequestDto rideRequestDto);
}