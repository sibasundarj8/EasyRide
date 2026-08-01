package com.sibasundarj8.project.easyride.easyrideApp.service.impl;

import com.sibasundarj8.project.easyride.easyrideApp.entity.User;
import com.sibasundarj8.project.easyride.easyrideApp.exception.ResourceNotFoundException;
import com.sibasundarj8.project.easyride.easyrideApp.repository.UserRepository;
import com.sibasundarj8.project.easyride.easyrideApp.service.ICurrentUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CurrentUserServiceImpl implements ICurrentUserService {
    private final UserRepository userRepository;

    @Override
    public User getCurrentUser() {
        // TODO: implement spring security here

        return userRepository
                .findById(1L)
                .orElseThrow(() -> new ResourceNotFoundException("User not found thrown by [CURRENT_USER_SERVICE]"));
    }
}