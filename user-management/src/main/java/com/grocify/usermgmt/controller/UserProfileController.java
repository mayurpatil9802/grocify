package com.grocify.usermgmt.controller;

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

    @PutMapping("/{id}")
    public UserResponse updateProfile(@PathVariable Long id, @RequestBody UserProfileUpdateRequest userProfileUpdateRequest) {
        return userProfileService.updateUserProfile(id, userProfileUpdateRequest);
    }

    @DeleteMapping("/{id}")
    public UserResponse deleteProfile(@PathVariable Long id) {
        return userProfileService.deleteUserProfile(id);
    }


}
