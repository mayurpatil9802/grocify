package com.grocify.commonlibs.model.response;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse {

    private String token;
    private UserResponse user;

}
