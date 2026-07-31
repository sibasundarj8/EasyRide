package com.sibasundarj8.project.easyride.easyrideApp.controller;

import com.sibasundarj8.project.easyride.easyrideApp.dto.OtpDto;
import com.sibasundarj8.project.easyride.easyrideApp.dto.RideDto;
import com.sibasundarj8.project.easyride.easyrideApp.service.IDriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/driver")
public class DriverController {

    private final IDriverService driverService;

    @PostMapping("/accept_ride/{rideRequestId}")
    public ResponseEntity<RideDto> acceptRide(@PathVariable Long rideRequestId) {
        return ResponseEntity.ok(driverService.acceptRide(rideRequestId));
    }

    @PostMapping("/cancel_ride/{rideId}")
    public ResponseEntity<RideDto> cancelRide(@PathVariable Long rideId) {
        return ResponseEntity.ok(driverService.cancelRide(rideId));
    }

    @PostMapping("/start_ride/{rideId}")
    public ResponseEntity<RideDto> startRide(@PathVariable Long rideId, @RequestBody OtpDto otp) {
        return ResponseEntity.ok(driverService.startRide(rideId, otp.getOtp()));
    }

    @PostMapping("/end_ride/{rideId}")
    public ResponseEntity<RideDto> endRide(@PathVariable Long rideId) {
        return ResponseEntity.ok(driverService.endRide(rideId));
    }
}