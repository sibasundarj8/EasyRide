package com.sibasundarj8.project.easyride.easyrideApp.controller;

import com.sibasundarj8.project.easyride.easyrideApp.dto.RideDto;
import com.sibasundarj8.project.easyride.easyrideApp.dto.RideRequestDto;
import com.sibasundarj8.project.easyride.easyrideApp.service.IRiderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/rider")
public class RiderController {

    private final IRiderService riderService;

    @PostMapping("/request_ride")
    public ResponseEntity<RideRequestDto> requestRide(@RequestBody RideRequestDto rideRequestDto) {
        RideRequestDto request = riderService.requestRide(rideRequestDto);
        return new ResponseEntity<>(request, HttpStatus.CREATED);
    }

    @PostMapping("/cancel_ride/{rideId}")
    public ResponseEntity<RideDto> cancelRide(@PathVariable Long rideId) {
        RideDto ride = riderService.cancelRide(rideId);
        return new ResponseEntity<>(ride, HttpStatus.CREATED);
    }
}