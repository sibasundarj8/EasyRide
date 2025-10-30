package com.sibasundarj8.project.easyride.easyrideApp.dtos;

import com.sibasundarj8.project.easyride.easyrideApp.entities.Rider;
import com.sibasundarj8.project.easyride.easyrideApp.entities.enums.PaymentMethod;
import com.sibasundarj8.project.easyride.easyrideApp.entities.enums.RideRequestStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RideRequestDto {
    private Long id;
    private Point pickupLocation;
    private Point dropOffLocation;
    private LocalDateTime requestedTime;
    private RiderDto riderDto;
    private PaymentMethod paymentMethod;
    private RideRequestStatus rideRequestStatus;
}