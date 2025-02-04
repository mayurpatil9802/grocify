package com.grocify.usermgmt.controller;


import com.grocify.commonlibs.model.request.LoginRequest;
import com.grocify.commonlibs.model.request.SignupRequest;
import com.grocify.commonlibs.model.response.LoginResponse;
import com.grocify.usermgmt.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @Autowired
    private AuthService authService;


    @PostMapping("/login")
    public LoginResponse userInformation(@RequestBody LoginRequest loginRequest){
        return authService.login(loginRequest);
    }

    @PostMapping("/signup")
    public LoginResponse signUpUserDetail(@RequestBody SignupRequest signupRequest){
        return authService.signUp(signupRequest);
    }

}
