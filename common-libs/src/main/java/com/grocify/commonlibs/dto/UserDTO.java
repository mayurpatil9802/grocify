package com.grocify.commonlibs.dto;

import com.grocify.commonlibs.enums.UserRole;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Map;

@Data
@Builder
public class UserDTO {
    private Long id;

    private String emailId;

    private String firstName;

    private String lastName;

    private String mobileNo;

    private String address;

    private String password;

    private UserRole role;

    private Boolean status;

    private Map<String, String> metadata;

    private LocalDate lastLogin;

}
