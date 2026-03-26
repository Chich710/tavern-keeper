package me.authapp.exeption;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(UserException.class)
    public ResponseEntity<String> catchException(UserException ex) {
        log.error(ex.getMessage());

        return ResponseEntity.status(ex.getCode()).body(ex.getMessage());
    }
}
