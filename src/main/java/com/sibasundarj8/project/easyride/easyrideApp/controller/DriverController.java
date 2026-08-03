package com.sibasundarj8.project.easyride.easyrideApp.controller;

import com.sibasundarj8.project.easyride.easyrideApp.dto.DriverDto;
import com.sibasundarj8.project.easyride.easyrideApp.dto.OtpDto;
import com.sibasundarj8.project.easyride.easyrideApp.dto.RateDto;
import com.sibasundarj8.project.easyride.easyrideApp.dto.RideDto;
import com.sibasundarj8.project.easyride.easyrideApp.service.IDriverService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/driver")
public class DriverController {

    private final IDriverService driverService;

    @GetMapping("/my_profile")
    public ResponseEntity<DriverDto> getMyProfile() {
        return ResponseEntity.ok(driverService.getMyProfile());
    }

    @GetMapping("/rides")
    public ResponseEntity<Page<RideDto>> getMyRides(@RequestParam(required = false, defaultValue = "0") Integer page) {

        Pageable pageable = PageRequest.of(page, 10, Sort.by("createdTime").descending());
        Page<RideDto> rides = driverService.getAllMyRides(pageable);
        return ResponseEntity.ok(rides);
    }

    @PostMapping("/ride_request/{rideRequestId}/accept_ride")
    public ResponseEntity<RideDto> acceptRide(@PathVariable Long rideRequestId) {
        return ResponseEntity.ok(driverService.acceptRide(rideRequestId));
    }

    @PostMapping("/rides/{rideId}/cancel_ride")
    public ResponseEntity<RideDto> cancelRide(@PathVariable Long rideId) {
        return ResponseEntity.ok(driverService.cancelRide(rideId));
    }

    @PostMapping("/rides/{rideId}/start_ride")
    public ResponseEntity<RideDto> startRide(@PathVariable Long rideId, @RequestBody OtpDto otp) {
        return ResponseEntity.ok(driverService.startRide(rideId, otp.getOtp()));
    }

    @PostMapping("/rides/{rideId}/end_ride")
    public ResponseEntity<RideDto> endRide(@PathVariable Long rideId) {
        return ResponseEntity.ok(driverService.endRide(rideId));
    }

    @PostMapping("/rides/{rideId}/rate_ride")
    public ResponseEntity<Void> rateRide(
            @PathVariable Long rideId,
            @Valid @RequestBody RateDto rateDto
    ) {

        driverService.rateRide(rideId, rateDto);
        return ResponseEntity.noContent().build();
    }
}