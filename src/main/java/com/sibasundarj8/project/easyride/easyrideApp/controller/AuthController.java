package com.sibasundarj8.project.easyride.easyrideApp.controller;

import com.sibasundarj8.project.easyride.easyrideApp.dto.SignupDto;
import com.sibasundarj8.project.easyride.easyrideApp.dto.UserDto;
import com.sibasundarj8.project.easyride.easyrideApp.service.IAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final IAuthService IAuthService;

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signUp(@RequestBody SignupDto signupDto) {
        return new ResponseEntity<>(IAuthService.signup(signupDto), HttpStatus.CREATED);
    }
}