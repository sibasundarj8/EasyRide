package com.sibasundarj8.project.easyride.easyrideApp.service.impl;

import com.sibasundarj8.project.easyride.easyrideApp.dto.DriverDto;
import com.sibasundarj8.project.easyride.easyrideApp.dto.OnboardDriverDto;
import com.sibasundarj8.project.easyride.easyrideApp.dto.SignupDto;
import com.sibasundarj8.project.easyride.easyrideApp.dto.UserDto;
import com.sibasundarj8.project.easyride.easyrideApp.entity.Driver;
import com.sibasundarj8.project.easyride.easyrideApp.entity.User;
import com.sibasundarj8.project.easyride.easyrideApp.entity.enums.Role;
import com.sibasundarj8.project.easyride.easyrideApp.exception.ResourceNotFoundException;
import com.sibasundarj8.project.easyride.easyrideApp.exception.RuntimeConflictException;
import com.sibasundarj8.project.easyride.easyrideApp.repository.UserRepository;
import com.sibasundarj8.project.easyride.easyrideApp.service.IAuthService;
import com.sibasundarj8.project.easyride.easyrideApp.service.IDriverService;
import com.sibasundarj8.project.easyride.easyrideApp.service.IRiderService;
import com.sibasundarj8.project.easyride.easyrideApp.service.IWalletService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements IAuthService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final IRiderService riderService;
    private final IDriverService driverService;
    private final IWalletService walletService;

    @Override
    public String login(String email, String password) {
        return "";
    }

    @Override
    @Transactional
    public UserDto signup(SignupDto signupDto) {
        if (userRepository.existsByEmail(signupDto.getEmail()))
            throw new RuntimeConflictException("Can't signup, User already exist with email " + signupDto.getEmail());

        User user = modelMapper.map(signupDto, User.class);
        user.getRoles().add(Role.RIDER);

        User savedUser = userRepository.save(user);
        riderService.createRider(savedUser);

        walletService.createNewWallet(savedUser);

        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    @Transactional
    public DriverDto onboardNewDriver(Long userId, OnboardDriverDto onboardDriverDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));

        if (user.getRoles().contains(Role.DRIVER)) {
            throw new IllegalStateException("User is already a driver with id : " + userId);
        }

        Driver driver = Driver.builder()
                .user(user)
                .vehicleNo(onboardDriverDto.getVehicleNo())
                .vehicleType(onboardDriverDto.getVehicleType())
                .build();

        user.getRoles().add(Role.DRIVER);

        return modelMapper.map(driverService.createNewDriver(driver), DriverDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    public User getCurrentUser() {
        return userRepository.findById(1L).orElseThrow(() -> new RuntimeException("User not found called from [authService]"));
    }
}