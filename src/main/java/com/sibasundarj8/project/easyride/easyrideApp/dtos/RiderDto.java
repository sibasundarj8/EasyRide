package com.sibasundarj8.project.easyride.easyrideApp.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RiderDto {
    private UserDto userDto;
    private Double rating;
}