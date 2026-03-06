package com.sibasundarj8.project.easyride.easyrideApp.strategy;

import com.sibasundarj8.project.easyride.easyrideApp.strategy.impl.DriverMatchingHighestRatedIDriverStrategy;
import com.sibasundarj8.project.easyride.easyrideApp.strategy.impl.DriverMatchingNearestIDriverStrategy;
import com.sibasundarj8.project.easyride.easyrideApp.strategy.impl.RideFareDefaultFareCalculationStrategy;
import com.sibasundarj8.project.easyride.easyrideApp.strategy.impl.RideFareSurgePricingFareCalculationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@RequiredArgsConstructor
@Component
public class RideStrategyManager {

    private final DriverMatchingNearestIDriverStrategy nearestDriverStrategy;
    private final DriverMatchingHighestRatedIDriverStrategy highestRatedDriverStrategy;
    private final RideFareDefaultFareCalculationStrategy defaultFareCalculationStrategy;
    private final RideFareSurgePricingFareCalculationStrategy surgePricingFareCalculationStrategy;

    public IDriverMatchingStrategy getDriverMatchingStrategy(double riderRating) {
        if (riderRating >= 4.8) return highestRatedDriverStrategy;
        else return nearestDriverStrategy;
    }

    public IRideFareCalculationStrategy getRideFareCalculationStrategy() {
        LocalTime startPeakTime = LocalTime.of(17, 0);
        LocalTime endPeakTime = LocalTime.of(21, 0);
        LocalTime currentTime = LocalTime.now();

        if (!currentTime.isBefore(startPeakTime) && !currentTime.isAfter(endPeakTime))
            return surgePricingFareCalculationStrategy;
        else return defaultFareCalculationStrategy;
    }
}