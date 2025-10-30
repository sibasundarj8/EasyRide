package com.sibasundarj8.project.easyride.easyrideApp.services.impl;

import com.sibasundarj8.project.easyride.easyrideApp.dtos.DriverDto;
import com.sibasundarj8.project.easyride.easyrideApp.dtos.SignupDto;
import com.sibasundarj8.project.easyride.easyrideApp.dtos.UserDto;
import com.sibasundarj8.project.easyride.easyrideApp.services.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Override
    public String login(String email, String password) {
        return "";
    }

    @Override
    public UserDto signup(SignupDto signupDto) {
        return null;
    }

    @Override
    public DriverDto onboardNewDriver(Long userId) {
        return null;
    }
}