package com.grocify.usermgmt.controller;

import com.grocify.commonlibs.model.request.UserPasswordUpdateRequest;
import com.grocify.commonlibs.model.request.UserProfileUpdateRequest;
import com.grocify.commonlibs.model.response.UserResponse;
import com.grocify.usermgmt.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserProfileController {

    @Autowired
    private UserProfileService userProfileService;

    @GetMapping("/{userId}")
    public UserResponse updateProfile(@PathVariable Long userId) {
        return userProfileService.getUserProfile(userId);
    }

    @PutMapping("/{id}")
    public UserResponse updateProfile(@PathVariable Long id, @RequestBody UserProfileUpdateRequest userProfileUpdateRequest) {
        return userProfileService.updateUserProfile(id, userProfileUpdateRequest);
    }

    @PutMapping("password/{id}")
    public UserResponse updatePassword(@PathVariable Long id, @RequestBody UserPasswordUpdateRequest userProfileUpdateRequest) {
        return userProfileService.updateUserPassword(id, userProfileUpdateRequest);
    }
    @PutMapping("address/{id}")
    public UserResponse updateUserAddress(@PathVariable Long id, @RequestBody String userProfileUpdateRequest) {
        return userProfileService.updateAddress(id, userProfileUpdateRequest);
    }

    @DeleteMapping("/{id}")
    public UserResponse deleteProfile(@PathVariable Long id) {
        return userProfileService.deleteUserProfile(id);
    }


}
