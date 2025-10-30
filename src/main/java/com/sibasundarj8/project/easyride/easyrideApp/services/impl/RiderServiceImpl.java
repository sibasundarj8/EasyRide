package com.sibasundarj8.project.easyride.easyrideApp.services.impl;

import com.sibasundarj8.project.easyride.easyrideApp.dtos.DriverDto;
import com.sibasundarj8.project.easyride.easyrideApp.dtos.RideDto;
import com.sibasundarj8.project.easyride.easyrideApp.dtos.RideRequestDto;
import com.sibasundarj8.project.easyride.easyrideApp.dtos.RiderDto;
import com.sibasundarj8.project.easyride.easyrideApp.services.RiderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RiderServiceImpl implements RiderService {

    @Override
    public RideRequestDto requestRide(RideRequestDto rideRequestDto) {
        return null;
    }

    @Override
    public RideDto cancelRide(Long rideId) {
        return null;
    }

    @Override
    public DriverDto reteDriver(Long RideId, Integer rating) {
        return null;
    }

    @Override
    public RiderDto getMyProfile() {
        return null;
    }

    @Override
    public List<RideDto> getAllMyRides() {
        return List.of();
    }
}