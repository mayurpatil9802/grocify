package com.grocify.usermgmt.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ConformPasswordNotMatch extends RuntimeException {
    private String message;
}
