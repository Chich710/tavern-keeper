package me.authapp.controller;

import lombok.RequiredArgsConstructor;
import me.authapp.dto.request.AuthorizationRequestDto;
import me.authapp.service.AccountService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AccountService accountService;

    @PostMapping("/login")
    public void login(@RequestBody AuthorizationRequestDto payload) { accountService.login(payload); }
}
