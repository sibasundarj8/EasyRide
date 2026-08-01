package com.sibasundarj8.project.easyride.easyrideApp.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RateDto {

    @Min(value = 1, message = "Rating should be at least 1 ⭐")
    @Max(value = 5, message = "Rating should be at most 5 ⭐")
    Integer rating;
}