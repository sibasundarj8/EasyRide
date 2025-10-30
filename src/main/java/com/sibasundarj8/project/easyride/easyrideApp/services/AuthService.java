package com.sibasundarj8.project.easyride.easyrideApp.services;

import com.sibasundarj8.project.easyride.easyrideApp.dtos.DriverDto;
import com.sibasundarj8.project.easyride.easyrideApp.dtos.SignupDto;
import com.sibasundarj8.project.easyride.easyrideApp.dtos.UserDto;

public interface AuthService {

    String login(String email, String password);

    UserDto signup(SignupDto signupDto);

    DriverDto onboardNewDriver(Long userId);
}