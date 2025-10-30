package com.sibasundarj8.project.easyride.easyrideApp.services.impl;

import com.sibasundarj8.project.easyride.easyrideApp.dtos.DriverDto;
import com.sibasundarj8.project.easyride.easyrideApp.dtos.RideDto;
import com.sibasundarj8.project.easyride.easyrideApp.dtos.RiderDto;
import com.sibasundarj8.project.easyride.easyrideApp.services.DriverService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverServiceImpl implements DriverService {

    @Override
    public RideDto acceptRide(Long rideId) {
        return null;
    }

    @Override
    public RideDto cancelRide(Long rideId) {
        return null;
    }

    @Override
    public RideDto startRide(Long rideId) {
        return null;
    }

    @Override
    public RideDto endRide(Long rideId) {
        return null;
    }

    @Override
    public RiderDto reteRider(Long RideId, Integer rating) {
        return null;
    }

    @Override
    public DriverDto getMyProfile() {
        return null;
    }

    @Override
    public List<RideDto> getAllMyRides() {
        return List.of();
    }
}
