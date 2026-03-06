package com.sibasundarj8.project.easyride.easyrideApp.service.impl;

import com.sibasundarj8.project.easyride.easyrideApp.entity.RideRequest;
import com.sibasundarj8.project.easyride.easyrideApp.exception.ResourceNotFoundException;
import com.sibasundarj8.project.easyride.easyrideApp.repository.RideRequestRepository;
import com.sibasundarj8.project.easyride.easyrideApp.service.IRideRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class RideRequestServiceImpl implements IRideRequestService {

    private final RideRequestRepository rideRequestRepository;


    @Override
    @Transactional(readOnly = true)
    public RideRequest getRideRequestById(Long rideRequestId) {

        return rideRequestRepository.findById(rideRequestId)
                .orElseThrow(() -> new ResourceNotFoundException("Ride Request Not Found with id: " + rideRequestId));
    }

    @Override
    @Transactional
    public void update(RideRequest rideRequest) {

        if(!rideRequestRepository.existsById(rideRequest.getId())) {
            throw new ResourceNotFoundException("Ride Request Not Found with id: " + rideRequest.getId());
        }

        rideRequestRepository.save(rideRequest);
    }
}