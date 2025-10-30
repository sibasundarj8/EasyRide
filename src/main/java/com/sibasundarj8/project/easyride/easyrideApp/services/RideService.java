package com.sibasundarj8.project.easyride.easyrideApp.services;

import com.sibasundarj8.project.easyride.easyrideApp.dtos.RideRequestDto;
import com.sibasundarj8.project.easyride.easyrideApp.entities.Driver;
import com.sibasundarj8.project.easyride.easyrideApp.entities.Ride;
import com.sibasundarj8.project.easyride.easyrideApp.entities.enums.RideStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface RideService {

    Ride getRideById(Long rideId);

    void matchWithRiders(RideRequestDto rideRequestDto);

    Ride createNewRide(RideRequestDto rideRequestDto, Driver driver);

    Ride updateRideStatus(Long rideId, RideStatus rideStatus);

    Page<Ride> getAllRidesOfRider(Long rideId, PageRequest pageRequest);

    Page<Ride> getAllRidesOfDriver(Long driverId, PageRequest pageRequest);
}