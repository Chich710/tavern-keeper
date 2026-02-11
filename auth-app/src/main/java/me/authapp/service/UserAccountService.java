package me.authapp.service;

import me.authapp.dto.request.UserRegistrationRequestDto;
import me.authapp.entity.UserEntity;

public interface UserAccountService {
    UserEntity register(UserRegistrationRequestDto payload);
}
