package com.sibasundarj8.project.easyride.easyrideApp.services.impl;

import com.sibasundarj8.project.easyride.easyrideApp.dtos.RideRequestDto;
import com.sibasundarj8.project.easyride.easyrideApp.entities.Driver;
import com.sibasundarj8.project.easyride.easyrideApp.entities.Ride;
import com.sibasundarj8.project.easyride.easyrideApp.entities.enums.RideStatus;
import com.sibasundarj8.project.easyride.easyrideApp.services.RideService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class RideServiceImpl implements RideService {

    @Override
    public Ride getRideById(Long rideId) {
        return null;
    }

    @Override
    public void matchWithRiders(RideRequestDto rideRequestDto) {

    }

    @Override
    public Ride createNewRide(RideRequestDto rideRequestDto, Driver driver) {
        return null;
    }

    @Override
    public Ride updateRideStatus(Long rideId, RideStatus rideStatus) {
        return null;
    }

    @Override
    public Page<Ride> getAllRidesOfRider(Long rideId, PageRequest pageRequest) {
        return null;
    }

    @Override
    public Page<Ride> getAllRidesOfDriver(Long driverId, PageRequest pageRequest) {
        return null;
    }
}
