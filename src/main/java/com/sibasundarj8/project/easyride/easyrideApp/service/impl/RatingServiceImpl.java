package com.sibasundarj8.project.easyride.easyrideApp.service.impl;

import com.sibasundarj8.project.easyride.easyrideApp.dto.RateDto;
import com.sibasundarj8.project.easyride.easyrideApp.entity.Driver;
import com.sibasundarj8.project.easyride.easyrideApp.entity.Rider;
import com.sibasundarj8.project.easyride.easyrideApp.repository.DriverRepository;
import com.sibasundarj8.project.easyride.easyrideApp.repository.RiderRepository;
import com.sibasundarj8.project.easyride.easyrideApp.service.IRatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class RatingServiceImpl implements IRatingService {

    private final RiderRepository riderRepository;
    private final DriverRepository driverRepository;

    @Override
    @Transactional
    public void rateDriver(Driver driver, RateDto rateDto) {
        driver.setRatingSum(driver.getRatingSum() + rateDto.getRating());
        driver.setRatingCount(driver.getRatingCount() + 1);
        driver.setRating((double) driver.getRatingSum() / driver.getRatingCount());

        driverRepository.save(driver);
    }

    @Override
    @Transactional
    public void rateRider(Rider rider, RateDto rateDto) {
        rider.setRatingSum(rider.getRatingSum() + rateDto.getRating());
        rider.setRatingCount(rider.getRatingCount() + 1);
        rider.setRating((double) rider.getRatingSum() / rider.getRatingCount());

        riderRepository.save(rider);
    }
}
