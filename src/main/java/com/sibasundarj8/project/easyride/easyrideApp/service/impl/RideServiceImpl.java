package com.sibasundarj8.project.easyride.easyrideApp.service.impl;

import com.sibasundarj8.project.easyride.easyrideApp.dto.RideDto;
import com.sibasundarj8.project.easyride.easyrideApp.dto.RideRequestDto;
import com.sibasundarj8.project.easyride.easyrideApp.entity.Driver;
import com.sibasundarj8.project.easyride.easyrideApp.entity.Ride;
import com.sibasundarj8.project.easyride.easyrideApp.entity.RideRequest;
import com.sibasundarj8.project.easyride.easyrideApp.entity.Rider;
import com.sibasundarj8.project.easyride.easyrideApp.entity.enums.RideRequestStatus;
import com.sibasundarj8.project.easyride.easyrideApp.entity.enums.RideStatus;
import com.sibasundarj8.project.easyride.easyrideApp.exception.ResourceNotFoundException;
import com.sibasundarj8.project.easyride.easyrideApp.repository.RideRepository;
import com.sibasundarj8.project.easyride.easyrideApp.repository.RiderRepository;
import com.sibasundarj8.project.easyride.easyrideApp.service.IRideRequestService;
import com.sibasundarj8.project.easyride.easyrideApp.service.IRideService;
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
    private final RiderRepository riderRepository;
    private final IRideRequestService rideRequestService;
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
}
