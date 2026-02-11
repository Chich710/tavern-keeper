package me.authapp.controller;

import lombok.RequiredArgsConstructor;
import me.authapp.dto.request.UserRegistrationRequestDto;
import me.authapp.entity.UserEntity;
import me.authapp.service.UserAccountService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserAccountService userAccountService;

    @PostMapping("/register")
    public UserEntity register(@RequestBody UserRegistrationRequestDto payload) {
        return userAccountService.register(payload);
    }
}
