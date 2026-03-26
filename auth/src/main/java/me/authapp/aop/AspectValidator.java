package me.authapp.aop;

import lombok.extern.slf4j.Slf4j;
import me.authapp.dto.request.UserRegistrationRequestDto;
import me.authapp.exeption.UserException;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class AspectValidator {
    private static final String POINTCUT_REGISTER_NEW_USER = "execution(* me.authapp.service.impl.UserServiceImpl.register(..))";
    private static final String LOGIN_PATTERN = "^[a-zA-Z_\\-]+$";
    private static final String PASSWORD_PATTERN = ".*[A-Z].*";

    // TODO: переделать осознанно
    @Before(value = POINTCUT_REGISTER_NEW_USER)
    public void validateRegisterParams(UserRegistrationRequestDto payload) {
        String login = payload.getLogin();
        if (login == null || !login.matches(LOGIN_PATTERN)) {
            throw new UserException("Login must contain only Latin letters, '_' or '-'", 400);
        }
        String password = payload.getPassword();
        if (password == null || password.length() < 6) {
            throw new UserException("Password must be at least 6 characters long", 400);
        }
        if (!password.matches(PASSWORD_PATTERN)) {
            throw new UserException("Password must contain at least one uppercase letter", 400);
        }
        log.info("Validation passed");
    }
}
