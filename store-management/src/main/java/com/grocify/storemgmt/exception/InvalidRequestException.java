package com.grocify.storemgmt.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class InvalidRequestException extends RuntimeException {

    private final String message;

    public InvalidRequestException(String message) {
        super(message);
        this.message = message;
    }
}
