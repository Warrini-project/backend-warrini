package com.fss.warrini.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {



    @ExceptionHandler(FacultyNotFoundException.class)
    public ResponseEntity<String> facultyNotFound(FacultyNotFoundException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> userNotFound(UserNotFoundException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(UserNameAlreadyUsedException.class)
    public ResponseEntity<String> userNameAlreadyUsed(UserNameAlreadyUsedException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
