package com.grocify.storemgmt.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class InvalidRequestException extends RuntimeException {

    private String message;

}
