package com.instaclone.instaclone.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PasswordTokenException extends RuntimeException {

    public PasswordTokenException(String message) {
        super(message);
    }
    
}
