package com.sibasundarj8.project.easyride.easyrideApp.dto;

import com.sibasundarj8.project.easyride.easyrideApp.entity.enums.VehicleType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OnboardDriverDto {
    private String vehicleNo;
    private VehicleType vehicleType;
}