package com.grocify.usermgmt.exception;

import lombok.Getter;

@Getter
public class InvalidCredentialsException extends RuntimeException{
    private final String message;

    public InvalidCredentialsException(String message) {
        super(message);
        this.message = message;
    }
}
