package com.grocify.commonlibs.mapper;

import com.grocify.commonlibs.dto.UserDTO;
import com.grocify.commonlibs.entity.UserEntity;
import com.grocify.commonlibs.model.request.SignupRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class UserMapper {

    public UserDTO userEntityToUserDTO(UserEntity userEntity) {
        return UserDTO.builder()
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .mobileNo(userEntity.getMobileNo())
                .address(userEntity.getAddress())
                .role(userEntity.getRole())
                .emailId(userEntity.getEmailId())
                .password(userEntity.getPassword())
                .lastLogin(userEntity.getLastLogin())
                .id(userEntity.getId())
                .metadata((userEntity.getMetadata()))
                .build();
    }
    public UserEntity signUpRequestToUserEntity(SignupRequest signupRequest){
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName(signupRequest.getFirstName());
        userEntity.setLastName(signupRequest.getLastName());
        userEntity.setMobileNo(signupRequest.getMobileNo());
        userEntity.setAddress(signupRequest.getAddress());
        userEntity.setRole(signupRequest.getRole());
        userEntity.setEmailId(signupRequest.getEmailId());
        userEntity.setPassword(signupRequest.getPassword());
        userEntity.setLastLogin(LocalDate.now());
        userEntity.setStatus(Boolean.TRUE);
        userEntity.setMetadata(signupRequest.getMetadata());
        return userEntity;
    }


    public UserEntity userDTOToUserEntity(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userDTO.getId());
        userEntity.setFirstName(userDTO.getFirstName());
        userEntity.setLastName(userDTO.getLastName());
        userEntity.setMobileNo(userDTO.getMobileNo());
        userEntity.setAddress(userDTO.getAddress());
        userEntity.setRole(userDTO.getRole());
        userEntity.setEmailId(userDTO.getEmailId());
        userEntity.setPassword(userDTO.getPassword());
        userEntity.setLastLogin(LocalDate.now());
        userEntity.setStatus(userDTO.getStatus());
        return userEntity;

    }


}
