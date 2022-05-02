package com.instaclone.instaclone.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmptyContentException extends RuntimeException{
    public EmptyContentException(String message) {
        super(message);
    }
}
