package com.sibasundarj8.project.easyride.easyrideApp.service;

import com.sibasundarj8.project.easyride.easyrideApp.dto.RateDto;
import com.sibasundarj8.project.easyride.easyrideApp.entity.Driver;
import com.sibasundarj8.project.easyride.easyrideApp.entity.Rider;

public interface IRatingService {

    void rateDriver(Driver driver, RateDto rateDto);

    void rateRider(Rider rider, RateDto rateDto);
}