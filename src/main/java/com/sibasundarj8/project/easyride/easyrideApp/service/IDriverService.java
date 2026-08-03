package com.sibasundarj8.project.easyride.easyrideApp.service;

import com.sibasundarj8.project.easyride.easyrideApp.dto.DriverDto;
import com.sibasundarj8.project.easyride.easyrideApp.dto.RateDto;
import com.sibasundarj8.project.easyride.easyrideApp.dto.RideDto;
import com.sibasundarj8.project.easyride.easyrideApp.entity.Driver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IDriverService {

    RideDto acceptRide(Long rideRequestId);

    RideDto cancelRide(Long rideId);

    RideDto startRide(Long rideId, String otp);

    RideDto endRide(Long rideId);

    void rateRide(Long rideId, RateDto rateDto);

    DriverDto getMyProfile();

    Page<RideDto> getAllMyRides(Pageable pageable);

    Driver getCurrentDriver();

    Driver updateAvailability(Driver driver, Boolean availability);

    Driver createNewDriver(Driver driver);
}