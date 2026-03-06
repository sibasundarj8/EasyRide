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

    @PostMapping("/acceptRide/{rideRequestId}")
    public ResponseEntity<RideDto> acceptRide(@PathVariable Long rideRequestId) {
        return ResponseEntity.ok(driverService.acceptRide(rideRequestId));
    }

    @PostMapping("/startRide/{rideId}")
    public ResponseEntity<RideDto> startRide(@PathVariable Long rideId, @RequestBody OtpDto otp) {
        return ResponseEntity.ok(driverService.startRide(rideId, otp.getOtp()));
    }
}