package com.sibasundarj8.project.easyride.easyrideApp.dto;

import com.sibasundarj8.project.easyride.easyrideApp.entity.enums.VehicleType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DriverDto {
    private UserDto user;
    private Double rating;
    private String vehicleNo;
    private VehicleType vehicleType;
}