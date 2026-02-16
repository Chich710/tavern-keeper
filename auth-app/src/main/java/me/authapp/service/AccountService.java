package me.authapp.service;

import me.authapp.dto.request.AuthorizationRequestDto;

public interface AccountService {
    void login(AuthorizationRequestDto payload);
}
