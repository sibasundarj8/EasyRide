package com.sibasundarj8.project.easyride.easyrideApp.dtos;

import com.sibasundarj8.project.easyride.easyrideApp.entities.Rider;
import com.sibasundarj8.project.easyride.easyrideApp.entities.enums.PaymentMethod;
import com.sibasundarj8.project.easyride.easyrideApp.entities.enums.RideStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RideDto {
    private Long id;
    private Point pickupLocation;
    private Point dropOffLocation;
    private LocalDateTime createdTime;
    private RiderDto riderDto;
    private DriverDto driverDto;
    private PaymentMethod paymentMethod;
    private RideStatus rideStatus;
    private Double fare;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;
}
