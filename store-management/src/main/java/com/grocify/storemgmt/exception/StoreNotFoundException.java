package com.grocify.storemgmt.exception;

import lombok.Getter;

@Getter
public class StoreNotFoundException extends RuntimeException{

    private final String message;

    public StoreNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
