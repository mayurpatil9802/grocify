package com.grocify.commonlibs.dao;

import com.grocify.commonlibs.dto.UserDTO;
import com.grocify.commonlibs.entity.UserEntity;
import com.grocify.commonlibs.mapper.UserMapper;
import com.grocify.commonlibs.model.request.SignupRequest;
import com.grocify.commonlibs.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthDao {

    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private UserMapper userMapper;

    public Optional<UserDTO> getUserByEmailId(String emailId) {
        Optional<UserEntity> userDetails = authRepository.findByEmailId(emailId);

        if (userDetails.isPresent() && userDetails.get().getStatus()){
            UserEntity user = userDetails.get();
            UserDTO userDto = userMapper.userEntityToUserDTO(user);
            return Optional.of(userDto);
        }

        return Optional.empty();
    }

    public Optional<UserDTO> getUserById(Long id) {
        Optional<UserEntity> userDetails = authRepository.findById(id);

        if (userDetails.isPresent() && userDetails.get().getStatus()){
            UserEntity user = userDetails.get();
            UserDTO userDto = userMapper.userEntityToUserDTO(user);
            return Optional.of(userDto);
        }

        return Optional.empty();
    }

    public Optional<UserDTO> insertUserInformation(SignupRequest signupRequest){
        UserEntity user = userMapper.signUpRequestToUserEntity(signupRequest);
        authRepository.save(user);
        UserDTO userDto = userMapper.userEntityToUserDTO(user);
        return Optional.of(userDto);
    }

    public UserDTO updateUserDetails(UserDTO userDTO) {
        UserEntity user = userMapper.userDTOToUserEntity(userDTO);
        user.setId(user.getId());
        authRepository.save(user);
        return userDTO;
    }

}
