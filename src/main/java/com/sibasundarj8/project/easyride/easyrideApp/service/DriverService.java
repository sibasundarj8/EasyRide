package com.sibasundarj8.project.easyride.easyrideApp.service;

import com.sibasundarj8.project.easyride.easyrideApp.dto.DriverDto;
import com.sibasundarj8.project.easyride.easyrideApp.dto.RideDto;
import com.sibasundarj8.project.easyride.easyrideApp.dto.RiderDto;

import java.util.List;

public interface DriverService {

    RideDto acceptRide(Long rideId);

    RideDto cancelRide(Long rideId);

    RideDto startRide(Long rideId);

    RideDto endRide(Long rideId);

    RiderDto reteRider(Long RideId, Integer rating);

    DriverDto getMyProfile();

    List<RideDto> getAllMyRides();
}