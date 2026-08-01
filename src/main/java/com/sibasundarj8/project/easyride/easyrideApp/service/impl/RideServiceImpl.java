package com.sibasundarj8.project.easyride.easyrideApp.service.impl;

import com.sibasundarj8.project.easyride.easyrideApp.dto.RateDto;
import com.sibasundarj8.project.easyride.easyrideApp.dto.RideDto;
import com.sibasundarj8.project.easyride.easyrideApp.dto.RideRequestDto;
import com.sibasundarj8.project.easyride.easyrideApp.entity.*;
import com.sibasundarj8.project.easyride.easyrideApp.entity.enums.RideRequestStatus;
import com.sibasundarj8.project.easyride.easyrideApp.entity.enums.RideStatus;
import com.sibasundarj8.project.easyride.easyrideApp.exception.ResourceNotFoundException;
import com.sibasundarj8.project.easyride.easyrideApp.repository.RideRepository;
import com.sibasundarj8.project.easyride.easyrideApp.service.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@RequiredArgsConstructor
@Service
public class RideServiceImpl implements IRideService {

    private final RideRepository rideRepository;
    private final IRideRequestService rideRequestService;
    private final ICurrentUserService currentUserService;
    private final IRatingService ratingService;
    private final ModelMapper modelMapper;


    @Override
    @Transactional(readOnly = true)
    public Ride getRideById(Long rideId) {

        return rideRepository.findById(rideId)
                .orElseThrow(() -> new ResourceNotFoundException("Ride not found with id: " + rideId));
    }

    @Override
    public void matchWithDrivers(RideRequestDto rideRequestDto) {

    }

    @Override
    @Transactional
    public Ride createNewRide(RideRequest rideRequest, Driver driver) {
        rideRequest.setRideRequestStatus(RideRequestStatus.CONFIRMED);

        Ride ride = modelMapper.map(rideRequest, Ride.class);

        ride.setDriver(driver);
        ride.setRideStatus(RideStatus.CONFIRMED);
        ride.setOtp(generateRandomOtp());
        ride.setId(null);

        rideRequestService.update(rideRequest);
        return rideRepository.save(ride);
    }

    @Override
    @Transactional
    public Ride updateRideStatus(Ride ride, RideStatus rideStatus) {
        ride.setRideStatus(rideStatus);
        return rideRepository.save(ride);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<RideDto> getAllRidesOfRider(Rider rider, Pageable pageable) {
         return rideRepository.findByRider(rider, pageable)
                 .map((element) -> modelMapper.map(element, RideDto.class));
    }

    @Override
    public Page<RideDto> getAllRidesOfDriver(Driver driver, Pageable pageable) {
        return rideRepository.findByDriver(driver, pageable)
                .map(ride -> modelMapper.map(ride, RideDto.class));
    }

    private String generateRandomOtp() {
        Random random = new Random();
        int otp = random.nextInt(10000);
        return String.format("%04d", otp);
    }

    @Override
    @Transactional
    public void rateRider(Long rideId, RateDto dto) {
        User user = currentUserService.getCurrentUser();
        Ride ride = getRideById(rideId);
        Rider rider = ride.getRider();
        Driver driver = ride.getDriver();

        if (!driver.getId().equals(user.getId())) {
            throw new RuntimeException("You are not associated with this ride, rideId: " + rideId);
        }

        if (!ride.getRideStatus().equals(RideStatus.ENDED)) {
            throw new IllegalStateException("Ride has not ended yet, rideId: " + rideId);
        }

        if (ride.isRiderRated()) {
            throw new IllegalStateException("Ride is already rated, rideId: " + rideId);
        } else {
            ratingService.rateRider(rider, dto);
            ride.setRiderRated(true);
        }
    }

    @Override
    @Transactional
    public void rateDriver(Long rideId, RateDto dto) {
        User user = currentUserService.getCurrentUser();
        Ride ride = getRideById(rideId);
        Rider rider = ride.getRider();
        Driver driver = ride.getDriver();

        if (!rider.getId().equals(user.getId())) {
            throw new RuntimeException("You are not associated with this ride, rideId: " + rideId);
        }

        if (!ride.getRideStatus().equals(RideStatus.ENDED)) {
            throw new IllegalStateException("Ride has not ended yet, rideId: " + rideId);
        }

        if (ride.isDriverRated()) {
            throw new IllegalStateException("Ride is already rated, rideId: " + rideId);
        } else {
            ratingService.rateDriver(driver, dto);
            ride.setDriverRated(true);
        }
    }
}