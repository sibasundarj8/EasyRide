package com.sibasundarj8.project.easyride.easyrideApp.service.impl;

import com.sibasundarj8.project.easyride.easyrideApp.dto.DriverDto;
import com.sibasundarj8.project.easyride.easyrideApp.dto.RateDto;
import com.sibasundarj8.project.easyride.easyrideApp.dto.RideDto;
import com.sibasundarj8.project.easyride.easyrideApp.entity.Driver;
import com.sibasundarj8.project.easyride.easyrideApp.entity.Ride;
import com.sibasundarj8.project.easyride.easyrideApp.entity.RideRequest;
import com.sibasundarj8.project.easyride.easyrideApp.entity.enums.RideRequestStatus;
import com.sibasundarj8.project.easyride.easyrideApp.entity.enums.RideStatus;
import com.sibasundarj8.project.easyride.easyrideApp.exception.ResourceNotFoundException;
import com.sibasundarj8.project.easyride.easyrideApp.repository.DriverRepository;
import com.sibasundarj8.project.easyride.easyrideApp.service.IDriverService;
import com.sibasundarj8.project.easyride.easyrideApp.service.IPaymentService;
import com.sibasundarj8.project.easyride.easyrideApp.service.IRideRequestService;
import com.sibasundarj8.project.easyride.easyrideApp.service.IRideService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class DriverServiceImpl implements IDriverService {

    private final DriverRepository driverRepository;

    private final IRideService rideService;
    private final IRideRequestService rideRequestService;
    private final IPaymentService paymentService;

    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public RideDto acceptRide(Long rideRequestId) {

        RideRequest rideRequest = rideRequestService.getRideRequestById(rideRequestId);

        // validating if request is already canceled or accepted by another driver or current driver is not available.
        validateRideRequest(rideRequest);

        // Set driver's availability to false : now driver is unable to accept any ride request
        Driver driver = this.getCurrentDriver();
        Driver savedDriver = updateAvailability(driver, false);

        // now a Ride object will be created with Driver and Fare and status will be conformed.
        Ride ride = rideService.createNewRide(rideRequest, savedDriver);

        return modelMapper.map(ride, RideDto.class);
    }

    @Override
    @Transactional
    public RideDto cancelRide(Long rideId) {
        Ride ride = rideService.getRideById(rideId);
        Driver driver = this.getCurrentDriver();

        if (!driver.equals(ride.getDriver())) {
            throw new RuntimeException("Driver can't be cancel a ride as he has not accepted the ride.");
        }

        if (!ride.getRideStatus().equals(RideStatus.CONFIRMED)) {
            throw new RuntimeException("Ride can't be canceled, invalid status: " + ride.getRideStatus());
        }

        ride.setRideStatus(RideStatus.CANCELLED);
        updateAvailability(driver, true);

        return modelMapper.map(ride, RideDto.class);
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

        paymentService.createPayment(savedRide);

        return modelMapper.map(savedRide, RideDto.class);
    }

    @Override
    @Transactional
    public RideDto endRide(Long rideId) {
        Ride ride = rideService.getRideById(rideId);
        Driver driver = this.getCurrentDriver();

        if (!driver.equals(ride.getDriver())) {
            throw new ResourceNotFoundException("Current driver is not associated with this ride with id: " + rideId);
        }

        if (!ride.getRideStatus().equals(RideStatus.ONGOING)) {
            throw new RuntimeException("Ride status is not ONGOING hence can't be ended, status: " + ride.getRideStatus());
        }

        ride.setEndedAt(LocalDateTime.now());
        Ride savedRide = rideService.updateRideStatus(ride, RideStatus.ENDED);

        paymentService.processPayment(ride);
        updateAvailability(driver, true);

        return modelMapper.map(savedRide, RideDto.class);
    }

    @Override
    @Transactional
    public void rateRide(Long rideId, RateDto rateDto) {
        rideService.rateRider(rideId, rateDto);
    }

    @Override
    public DriverDto getMyProfile() {
        Driver driver = this.getCurrentDriver();
        return modelMapper.map(driver, DriverDto.class);
    }

    @Override
    public Page<RideDto> getAllMyRides(Pageable pageable) {
        Driver driver = this.getCurrentDriver();
        return rideService.getAllRidesOfDriver(driver, pageable)
                .map((ride) -> modelMapper.map(ride, RideDto.class));
    }

    private void validateRideRequest(RideRequest rideRequest) {

        if (!rideRequest.getRideRequestStatus().equals(RideRequestStatus.PENDING)) {
            throw new RuntimeException("Can't accept, Ride Request is already " + rideRequest.getRideRequestStatus());
        }

        if (!getCurrentDriver().getAvailable()) {
            throw new RuntimeException("Can't accept ride due to unavailability !!");
        }
    }

    @Override
    public Driver getCurrentDriver() {

        // TODO : implement Spring security here

        return driverRepository.findById(15L)
                .orElseThrow(() -> new ResourceNotFoundException("Current Driver not found !!"));
    }

    @Override
    @Transactional
    public Driver updateAvailability(Driver driver, Boolean availability) {
        driver.setAvailable(availability);
        return driverRepository.save(driver);
    }

    @Override
    @Transactional
    public Driver createNewDriver(Driver driver) {
        return driverRepository.save(driver);
    }
}