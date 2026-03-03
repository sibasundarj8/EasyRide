package com.sibasundarj8.project.easyride.easyrideApp.service.impl;

import com.sibasundarj8.project.easyride.easyrideApp.dto.DriverDto;
import com.sibasundarj8.project.easyride.easyrideApp.dto.RideDto;
import com.sibasundarj8.project.easyride.easyrideApp.dto.RideRequestDto;
import com.sibasundarj8.project.easyride.easyrideApp.dto.RiderDto;
import com.sibasundarj8.project.easyride.easyrideApp.entity.Driver;
import com.sibasundarj8.project.easyride.easyrideApp.entity.RideRequest;
import com.sibasundarj8.project.easyride.easyrideApp.entity.Rider;
import com.sibasundarj8.project.easyride.easyrideApp.entity.User;
import com.sibasundarj8.project.easyride.easyrideApp.entity.enums.RideRequestStatus;
import com.sibasundarj8.project.easyride.easyrideApp.repository.RideRequestRepository;
import com.sibasundarj8.project.easyride.easyrideApp.repository.RiderRepository;
import com.sibasundarj8.project.easyride.easyrideApp.service.RiderService;
import com.sibasundarj8.project.easyride.easyrideApp.strategy.DriverMatchingStrategy;
import com.sibasundarj8.project.easyride.easyrideApp.strategy.RideFareCalculationStrategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class RiderServiceImpl implements RiderService {
    private final RiderRepository riderRepository;

    private final ModelMapper modelMapper;
    private final RideFareCalculationStrategy fareCalculationStrategy;
    private final Map<String, DriverMatchingStrategy> strategies;
    private final RideRequestRepository rideRequestRepository;

    @Value("${driver.matching.strategy}")
    private String strategyName;

    private List<Driver> matchDrivers(RideRequest rideRequest) {
        DriverMatchingStrategy strategy = strategies.get(strategyName);

        if (strategy == null) {
            throw new IllegalStateException("Strategy " + strategyName + " not found");
        }

        return strategy.findMatchingDrivers(rideRequest);
    }

    @Override
    public RideRequestDto requestRide(RideRequestDto rideRequestDto) {
        RideRequest rideRequest = modelMapper.map(rideRequestDto, RideRequest.class);
        rideRequest.setRideRequestStatus(RideRequestStatus.PENDING);

        Double fare = fareCalculationStrategy.calculateFare(rideRequest);
        rideRequest.setFare(fare);

        RideRequest savedRideRequest = rideRequestRepository.save(rideRequest);
        matchDrivers(savedRideRequest);

        return modelMapper.map(savedRideRequest, RideRequestDto.class);
    }

    @Override
    public RideDto cancelRide(Long rideId) {
        return null;
    }

    @Override
    public DriverDto reteDriver(Long RideId, Integer rating) {
        return null;
    }

    @Override
    public RiderDto getMyProfile() {
        return null;
    }

    @Override
    public List<RideDto> getAllMyRides() {
        return List.of();
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
}