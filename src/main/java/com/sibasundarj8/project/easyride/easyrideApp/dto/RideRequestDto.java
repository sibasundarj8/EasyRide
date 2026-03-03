package com.sibasundarj8.project.easyride.easyrideApp.dto;

import com.sibasundarj8.project.easyride.easyrideApp.entity.enums.PaymentMethod;
import com.sibasundarj8.project.easyride.easyrideApp.entity.enums.RideRequestStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RideRequestDto {
    private Long id;

    private PointDto pickupLocation;
    private PointDto dropOffLocation;
    private PaymentMethod paymentMethod;

    private LocalDateTime requestedTime;

    private RiderDto riderDto;

    private RideRequestStatus rideRequestStatus;
}