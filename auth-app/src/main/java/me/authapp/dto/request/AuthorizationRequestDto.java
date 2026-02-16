package me.authapp.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorizationRequestDto {
    String login;
    String password;
}
