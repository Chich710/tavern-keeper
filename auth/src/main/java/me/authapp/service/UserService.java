package me.authapp.service;

import me.authapp.dto.request.UserRegistrationRequestDto;
import me.authapp.entity.UserEntity;

public interface UserService {
    UserEntity register(UserRegistrationRequestDto payload);
}
