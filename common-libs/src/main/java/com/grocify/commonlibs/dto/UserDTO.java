package com.grocify.commonlibs.dto;

import com.grocify.commonlibs.enums.UserRole;
import lombok.Builder;
import lombok.Data;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Data
@Builder
public class UserDTO  {
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

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return List.of();
//    }
//
//    @Override
//    public String getUsername() {
//        return "";
//    }
}
