package com.glory.teamaptproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class UserExistException extends Exception {
    public UserExistException(String message) {
        super(message);
    }
}
