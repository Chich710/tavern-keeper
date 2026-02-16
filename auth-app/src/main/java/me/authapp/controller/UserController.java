package me.authapp.controller;

import lombok.RequiredArgsConstructor;
import me.authapp.dto.request.UserRegistrationRequestDto;
import me.authapp.entity.UserEntity;
import me.authapp.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public UserEntity register(@RequestBody UserRegistrationRequestDto payload) { return userService.register(payload); }
}
