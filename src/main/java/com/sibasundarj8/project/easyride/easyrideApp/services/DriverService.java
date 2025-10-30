package com.sibasundarj8.project.easyride.easyrideApp.services;

import com.sibasundarj8.project.easyride.easyrideApp.dtos.DriverDto;
import com.sibasundarj8.project.easyride.easyrideApp.dtos.RideDto;
import com.sibasundarj8.project.easyride.easyrideApp.dtos.RiderDto;

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