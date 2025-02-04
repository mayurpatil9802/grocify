package com.grocify.commonlibs.model.request;

import com.grocify.commonlibs.enums.UserRole;
import lombok.Data;

import java.util.Map;


@Data
public class SignupRequest {

    private String emailId;

    private String firstName;

    private String lastName;

    private String mobileNo;

    private String address;

    private String password;

//    private String confirmPassword;

    private Map<String, String> metadata;

    private UserRole role;

}
