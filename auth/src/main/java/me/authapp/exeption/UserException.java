package me.authapp.exeption;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserException extends RuntimeException {
    public static final String ROLE_DOES_NOT_EXIST = "Role does not exist";
    public static final String PASSWORD_IS_MISSING = "Password is missing";
    private Integer code;

    public UserException(String message, Integer code) {
        super(message);
        this.code = code;
    }
}
