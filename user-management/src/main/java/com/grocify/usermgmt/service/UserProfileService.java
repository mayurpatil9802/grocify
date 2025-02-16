package com.grocify.usermgmt.service;

import com.grocify.commonlibs.dao.AuthDao;
import com.grocify.commonlibs.dto.UserDTO;
import com.grocify.commonlibs.entity.UserEntity;
import com.grocify.commonlibs.model.request.UserPasswordUpdateRequest;
import com.grocify.commonlibs.model.request.UserProfileUpdateRequest;
import com.grocify.commonlibs.model.response.UserResponse;
import com.grocify.usermgmt.exception.UserNotFoundException;
import com.grocify.usermgmt.utility.ResponseBuilder;
import com.grocify.usermgmt.validator.UpdateRequestValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserProfileService {

    @Autowired
    private AuthDao authDao;

    @Autowired
    private UpdateRequestValidation updateRequestValidation;

    @Autowired
    private ResponseBuilder responseBuilder;

    public UserResponse updateUserProfile(Long id, UserProfileUpdateRequest userProfileUpdateRequest) {
        updateRequestValidation.validationUpdateRequest(userProfileUpdateRequest);

        Optional<UserDTO> existingUserDetails = authDao.getUserById(id);

        if (existingUserDetails.isEmpty()) {
            throw new UserNotFoundException("User with provided emailId is not present");
        }

        UserDTO userDTO = existingUserDetails.get();
        userDTO.setFirstName(userProfileUpdateRequest.getFirstName());
        userDTO.setLastName(userProfileUpdateRequest.getLastName());
        userDTO.setMobileNo(userProfileUpdateRequest.getMobileNo());
        userDTO.setAddress(userProfileUpdateRequest.getAddress());
        userDTO.setStatus(Boolean.TRUE);
        userDTO.setMetadata(userProfileUpdateRequest.getMetadata());

        userDTO = authDao.updateUserDetails(userDTO);
        return responseBuilder.userDTOToUserResponse(userDTO);
    }

    public UserResponse deleteUserProfile(Long id) {

        Optional<UserDTO> existingUserDetails = authDao.getUserById(id);

        if (existingUserDetails.isEmpty()) {
            throw new UserNotFoundException("User with provided emailId is not present");
        }

        UserDTO userDTO = existingUserDetails.get();
        userDTO.setStatus(Boolean.FALSE);

        userDTO = authDao.updateUserDetails(userDTO);

        return responseBuilder.userDTOToUserResponse(userDTO);
    }

	public UserResponse updateAddress(Long id, String userProfileUpdateRequest) {

        Optional<UserDTO> existingUserDetails = authDao.getUserById(id);

        if (existingUserDetails.isEmpty()) {
            throw new UserNotFoundException("User with provided emailId is not present");
        }
        UserDTO userDTO = existingUserDetails.get();
        userDTO.setAddress(userProfileUpdateRequest);
        userDTO.setStatus(Boolean.TRUE);
        userDTO = authDao.updateUserDetails(userDTO);
        return responseBuilder.userDTOToUserResponse(userDTO);
	}

    public UserResponse getUserProfile(Long userId) {

        Optional<UserDTO> userDTO=authDao.getUserById(userId);
        if (userDTO.isEmpty()) {
            throw new UserNotFoundException("User with provided emailId is not present");
        }
        return responseBuilder.userDTOToUserResponse(userDTO.get());
    }

    public UserResponse updateUserPassword(Long id, UserPasswordUpdateRequest userProfileUpdateRequest) {
        Optional<UserDTO> existingUserDetails = authDao.getUserById(id);

        if (existingUserDetails.isEmpty()) {
            throw new UserNotFoundException("User with provided emailId is not present");
        }

        UserDTO userDTO = existingUserDetails.get();

        userDTO.setPassword(userProfileUpdateRequest.getPassword());
        userDTO.setStatus(true);
        userDTO = authDao.updateUserDetails(userDTO);
        return responseBuilder.userDTOToUserResponse(userDTO);

    }
}
