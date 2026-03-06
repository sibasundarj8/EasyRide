package com.sibasundarj8.project.easyride.easyrideApp.service;

import com.sibasundarj8.project.easyride.easyrideApp.entity.RideRequest;

public interface IRideRequestService {

    RideRequest getRideRequestById(Long rideRequestId);

    void update(RideRequest rideRequest);
}