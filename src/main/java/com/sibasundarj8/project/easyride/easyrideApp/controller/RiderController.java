package com.sibasundarj8.project.easyride.easyrideApp.controller;

import com.sibasundarj8.project.easyride.easyrideApp.dto.RateDto;
import com.sibasundarj8.project.easyride.easyrideApp.dto.RideDto;
import com.sibasundarj8.project.easyride.easyrideApp.dto.RideRequestDto;
import com.sibasundarj8.project.easyride.easyrideApp.dto.RiderDto;
import com.sibasundarj8.project.easyride.easyrideApp.service.IRiderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/rider")
public class RiderController {

    private final IRiderService riderService;

    @GetMapping("/my_profile")
    public ResponseEntity<RiderDto> getMyProfile() {
        return ResponseEntity.ok(riderService.getMyProfile());
    }

    @GetMapping("/rides")
    public ResponseEntity<Page<RideDto>> getMyRides(
            @RequestParam(required = false, defaultValue = "0") Integer page) {

        Pageable pageable = PageRequest.of(page, 10, Sort.by("createdTime").descending());
        Page<RideDto> rides = riderService.getAllMyRides(pageable);
        return ResponseEntity.ok(rides);
    }

    @PostMapping("/request_ride")
    public ResponseEntity<RideRequestDto> requestRide(@RequestBody RideRequestDto rideRequestDto) {
        RideRequestDto request = riderService.requestRide(rideRequestDto);
        return new ResponseEntity<>(request, HttpStatus.CREATED);
    }

    @PostMapping("/rides/{rideId}/cancel_ride")
    public ResponseEntity<RideDto> cancelRide(@PathVariable Long rideId) {
        RideDto ride = riderService.cancelRide(rideId);
        return new ResponseEntity<>(ride, HttpStatus.CREATED);
    }

    @PostMapping("/rides/{rideId}/rate_ride")
    public ResponseEntity<Void> rateRide(
            @PathVariable Long rideId,
            @Valid @RequestBody RateDto rateDto
    ) {

        riderService.rateRide(rideId, rateDto);
        return ResponseEntity.noContent().build();
    }
}