package com.grocify.usermgmt.controller;


import com.grocify.commonlibs.model.request.LoginRequest;
import com.grocify.commonlibs.model.request.SignupRequest;
import com.grocify.commonlibs.model.response.LoginResponse;
import com.grocify.usermgmt.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
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
