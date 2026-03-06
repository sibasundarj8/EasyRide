package com.sibasundarj8.project.easyride.easyrideApp.service.impl;

import com.sibasundarj8.project.easyride.easyrideApp.dto.DriverDto;
import com.sibasundarj8.project.easyride.easyrideApp.dto.RideDto;
import com.sibasundarj8.project.easyride.easyrideApp.dto.RiderDto;
import com.sibasundarj8.project.easyride.easyrideApp.entity.Driver;
import com.sibasundarj8.project.easyride.easyrideApp.entity.Ride;
import com.sibasundarj8.project.easyride.easyrideApp.entity.RideRequest;
import com.sibasundarj8.project.easyride.easyrideApp.entity.enums.RideRequestStatus;
import com.sibasundarj8.project.easyride.easyrideApp.entity.enums.RideStatus;
import com.sibasundarj8.project.easyride.easyrideApp.exception.ResourceNotFoundException;
import com.sibasundarj8.project.easyride.easyrideApp.repository.DriverRepository;
import com.sibasundarj8.project.easyride.easyrideApp.service.IDriverService;
import com.sibasundarj8.project.easyride.easyrideApp.service.IRideRequestService;
import com.sibasundarj8.project.easyride.easyrideApp.service.IRideService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class DriverServiceImpl implements IDriverService {

    private final DriverRepository driverRepository;

    private final IRideService rideService;
    private final IRideRequestService rideRequestService;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public RideDto acceptRide(Long rideRequestId) {

        RideRequest rideRequest = rideRequestService.getRideRequestById(rideRequestId);

        // validating if request is already canceled or accepted by another driver or current driver is not available.
        validateRideRequest(rideRequest);

        // Set driver's availability to false : now driver is unable to accept any ride request
        Driver driver = this.getCurrentDriver();
        driver.setAvailable(false);
        Driver savedDriver = driverRepository.save(driver);

        // now a Ride object will be created with Driver and Fare and status will be conformed.
        Ride ride = rideService.createNewRide(rideRequest, savedDriver);

        return modelMapper.map(ride, RideDto.class);
    }

    @Override
    public RideDto cancelRide(Long rideId) {
        return null;
    }

    @Override
    @Transactional
    public RideDto startRide(Long rideId, String otp) {
        Ride ride = rideService.getRideById(rideId);
        Driver driver = this.getCurrentDriver();

        if (!driver.equals(ride.getDriver())) {
            throw new ResourceNotFoundException("Driver can't start ride as he hasn't accepted it earlier !!");
        }

        if (!ride.getRideStatus().equals(RideStatus.CONFIRMED)) {
            throw new RuntimeException("Ride status is not CONFIRMED hence can't be started, status: " + ride.getRideStatus());
        }

        if (!otp.equals(ride.getOtp())) {
            throw new RuntimeException("OTP is not valid, otp: " + otp);
        }

        ride.setStartedAt(LocalDateTime.now());
        Ride savedRide = rideService.updateRideStatus(ride, RideStatus.ONGOING);

        return modelMapper.map(savedRide, RideDto.class);
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

    private void validateRideRequest(RideRequest rideRequest) {

        if (!rideRequest.getRideRequestStatus().equals(RideRequestStatus.PENDING)) {
            throw new RuntimeException("Can't accept, Ride Request is already " + rideRequest.getRideRequestStatus());
        }

        if (!getCurrentDriver().getAvailable()) {
            throw new RuntimeException("Can't accept ride due to unavailability !!");
        }
    }

    private Driver getCurrentDriver() {

        // TODO : implement Spring security here

        return driverRepository.findById(15L)
                .orElseThrow(() -> new ResourceNotFoundException("Current Driver not found !!"));
    }
}