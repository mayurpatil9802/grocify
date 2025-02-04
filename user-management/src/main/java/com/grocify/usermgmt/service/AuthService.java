package com.grocify.usermgmt.service;

import com.grocify.commonlibs.dao.AuthDao;
import com.grocify.commonlibs.dto.UserDTO;
import com.grocify.commonlibs.model.request.LoginRequest;
import com.grocify.commonlibs.model.request.SignupRequest;
import com.grocify.commonlibs.model.response.LoginResponse;
import com.grocify.usermgmt.exception.InvalidCredentialsException;
import com.grocify.usermgmt.exception.UserNotFoundException;
import com.grocify.usermgmt.utility.JWTUtility;
import com.grocify.usermgmt.utility.ResponseBuilder;
import com.grocify.usermgmt.validator.AuthRequestValidator;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.beans.JavaBean;
import java.util.Optional;


@Service
@Transactional
public class AuthService {

    @Autowired
    private AuthRequestValidator authRequestValidator;

    @Autowired
    private AuthDao authDao;

    @Autowired
    private ResponseBuilder responseBuilder;

    @Autowired
    private JWTUtility jwtUtility;

    public LoginResponse login(LoginRequest loginRequest) {
        authRequestValidator.validateLoginRequest(loginRequest);
        Optional<UserDTO> optionalUserDetails = authDao.getUserByEmailId(loginRequest.getUsername());


        if (optionalUserDetails.isEmpty()) {
            throw new UserNotFoundException("User with provided emailId is not present");
        }

        UserDTO user = optionalUserDetails.get();

        if (!user.getPassword().equals(loginRequest.getPassword())) {
            throw new InvalidCredentialsException("incorrect password");
        }

        return responseBuilder.buildLoginResponse(jwtUtility.buildJWT(user), user);
    }


    public LoginResponse signUp(SignupRequest signupRequest) {
        authRequestValidator.validateSignUpRequest(signupRequest);
        Optional<UserDTO> user = authDao.insertUserInformation(signupRequest);
        return responseBuilder.buildLoginResponse(jwtUtility.buildJWT(user.get()), user.get());
    }
}
