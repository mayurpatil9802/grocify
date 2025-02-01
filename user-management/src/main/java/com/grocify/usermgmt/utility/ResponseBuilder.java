package com.grocify.usermgmt.utility;

import com.grocify.commonlibs.dto.UserDTO;
import com.grocify.commonlibs.model.response.LoginResponse;
import com.grocify.commonlibs.model.response.UserResponse;
import org.springframework.stereotype.Component;


@Component
public class ResponseBuilder {

    public LoginResponse buildLoginResponse(String token, UserDTO userDTO) {

        UserResponse userResponse = userDTOToUserResponse(userDTO);

        return LoginResponse.builder()
                .user(userResponse)
                .token(token)
                .build();

    }

    public UserResponse userDTOToUserResponse(UserDTO userDTO) {
        return UserResponse.builder()
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .role(userDTO.getRole().name())
                .emailId(userDTO.getEmailId())
                .mobileNo(userDTO.getMobileNo())
                .address(userDTO.getAddress())
                .metadata(userDTO.getMetadata())
                .user_id(userDTO.getId())
                .build();
    }


}
