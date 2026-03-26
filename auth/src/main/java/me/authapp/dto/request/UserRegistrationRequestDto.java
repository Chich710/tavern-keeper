package me.authapp.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationRequestDto {
    String name;
    String login;
    String password;
}
