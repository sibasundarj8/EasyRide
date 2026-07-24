package com.sibasundarj8.project.easyride.easyrideApp.service;

import com.sibasundarj8.project.easyride.easyrideApp.dto.DriverDto;
import com.sibasundarj8.project.easyride.easyrideApp.dto.RideDto;
import com.sibasundarj8.project.easyride.easyrideApp.dto.RideRequestDto;
import com.sibasundarj8.project.easyride.easyrideApp.dto.RiderDto;
import com.sibasundarj8.project.easyride.easyrideApp.entity.Rider;
import com.sibasundarj8.project.easyride.easyrideApp.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IRiderService {

    RideRequestDto requestRide(RideRequestDto rideRequestDto);

    RideDto cancelRide(Long rideId);

    DriverDto reteDriver(Long RideId, Integer rating);

    RiderDto getMyProfile();

    Page<RideDto> getAllMyRides(Pageable pageable);

    Rider createRider(User user);

    Rider getCurrentRider();
}
