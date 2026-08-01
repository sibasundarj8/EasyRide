package com.sibasundarj8.project.easyride.easyrideApp.service;

import com.sibasundarj8.project.easyride.easyrideApp.dto.RateDto;
import com.sibasundarj8.project.easyride.easyrideApp.dto.RideDto;
import com.sibasundarj8.project.easyride.easyrideApp.dto.RideRequestDto;
import com.sibasundarj8.project.easyride.easyrideApp.entity.Driver;
import com.sibasundarj8.project.easyride.easyrideApp.entity.Ride;
import com.sibasundarj8.project.easyride.easyrideApp.entity.RideRequest;
import com.sibasundarj8.project.easyride.easyrideApp.entity.Rider;
import com.sibasundarj8.project.easyride.easyrideApp.entity.enums.RideStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IRideService {

    Ride getRideById(Long rideId);

    void matchWithDrivers(RideRequestDto rideRequestDto);

    Ride createNewRide(RideRequest rideRequest, Driver driver);

    Ride updateRideStatus(Ride ride, RideStatus rideStatus);

    Page<RideDto> getAllRidesOfRider(Rider rider, Pageable pageable);

    Page<RideDto> getAllRidesOfDriver(Driver driver, Pageable pageable);

    void rateRider(Long rideId, RateDto dto);

    void rateDriver(Long rideId, RateDto dto);
}