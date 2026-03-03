package com.sibasundarj8.project.easyride.easyrideApp.service.impl;

import com.sibasundarj8.project.easyride.easyrideApp.dto.DriverDto;
import com.sibasundarj8.project.easyride.easyrideApp.dto.SignupDto;
import com.sibasundarj8.project.easyride.easyrideApp.dto.UserDto;
import com.sibasundarj8.project.easyride.easyrideApp.entity.User;
import com.sibasundarj8.project.easyride.easyrideApp.entity.enums.Role;
import com.sibasundarj8.project.easyride.easyrideApp.exception.RuntimeConflictException;
import com.sibasundarj8.project.easyride.easyrideApp.repository.UserRepository;
import com.sibasundarj8.project.easyride.easyrideApp.service.AuthService;
import com.sibasundarj8.project.easyride.easyrideApp.service.RiderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final RiderService riderService;

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

        // TODO wallet related service here

        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public DriverDto onboardNewDriver(Long userId) {
        return null;
    }
}