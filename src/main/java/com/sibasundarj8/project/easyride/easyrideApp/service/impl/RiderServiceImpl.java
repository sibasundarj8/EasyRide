package com.sibasundarj8.project.easyride.easyrideApp.service.impl;

import com.sibasundarj8.project.easyride.easyrideApp.dto.DriverDto;
import com.sibasundarj8.project.easyride.easyrideApp.dto.RideDto;
import com.sibasundarj8.project.easyride.easyrideApp.dto.RideRequestDto;
import com.sibasundarj8.project.easyride.easyrideApp.dto.RiderDto;
import com.sibasundarj8.project.easyride.easyrideApp.entity.*;
import com.sibasundarj8.project.easyride.easyrideApp.entity.enums.RideRequestStatus;
import com.sibasundarj8.project.easyride.easyrideApp.entity.enums.RideStatus;
import com.sibasundarj8.project.easyride.easyrideApp.exception.ResourceNotFoundException;
import com.sibasundarj8.project.easyride.easyrideApp.repository.RideRequestRepository;
import com.sibasundarj8.project.easyride.easyrideApp.repository.RiderRepository;
import com.sibasundarj8.project.easyride.easyrideApp.service.IDriverService;
import com.sibasundarj8.project.easyride.easyrideApp.service.IRideService;
import com.sibasundarj8.project.easyride.easyrideApp.service.IRiderService;
import com.sibasundarj8.project.easyride.easyrideApp.strategy.RideStrategyManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RiderServiceImpl implements IRiderService {

    private final RiderRepository riderRepository;
    private final ModelMapper modelMapper;
    private final RideRequestRepository rideRequestRepository;
    private final RideStrategyManager rideStrategyManager;
    private final IRideService rideService;
    private final IDriverService driverService;

    @Override
    @Transactional
    public RideRequestDto requestRide(RideRequestDto rideRequestDto) {
        Rider rider = getCurrentRider();
        RideRequest rideRequest = modelMapper.map(rideRequestDto, RideRequest.class);

        rideRequest.setRideRequestStatus(RideRequestStatus.PENDING);
        rideRequest.setRider(rider);

        Double fare = rideStrategyManager.getRideFareCalculationStrategy().calculateFare(rideRequest);
        rideRequest.setFare(fare);

        RideRequest savedRideRequest = rideRequestRepository.save(rideRequest);
        List<Driver> drivers = rideStrategyManager.getDriverMatchingStrategy(rider.getRating()).findMatchingDrivers(rideRequest);

        // TODO : Send notification to all the drivers about this ride.

        return modelMapper.map(savedRideRequest, RideRequestDto.class);
    }

    @Override
    @Transactional
    public RideDto cancelRide(Long rideId) {
        Ride ride = rideService.getRideById(rideId);
        Rider rider = getCurrentRider();

        if (!rider.equals(ride.getRider())) {
            throw new RuntimeException("Rider don't own the ride with rideId: " + rideId);
        }

        if (!ride.getRideStatus().equals(RideStatus.CONFIRMED)) {
            throw new RuntimeException("Ride can't be canceled, ride is: " + ride.getRideStatus());
        }

        ride.setRideStatus(RideStatus.CANCELLED);
        driverService.updateAvailability(ride.getDriver(), true);

        return modelMapper.map(ride, RideDto.class);
    }

    @Override
    public DriverDto reteDriver(Long RideId, Integer rating) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public RiderDto getMyProfile() {
        Rider rider = getCurrentRider();
        return modelMapper.map(rider, RiderDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<RideDto> getAllMyRides(Pageable pageable) {
        Rider rider = this.getCurrentRider();
        return rideService.getAllRidesOfRider(rider, pageable)
                .map((ride) -> modelMapper.map(ride, RideDto.class));
    }

    @Override
    @Transactional
    public Rider createRider(User user) {
        Rider rider = Rider.builder()
                .user(user)
                .rating(0.0)
                .build();

        return riderRepository.save(rider);
    }

    @Override
    public Rider getCurrentRider() {
        // TODO : implement spring security

        return riderRepository.findById(1L).orElseThrow(() -> new ResourceNotFoundException("Rider not found with id: " + 1));
    }
}