//package com.grocify.commonlibs.security;
//
//import com.grocify.commonlibs.entity.UserEntity;
//import com.grocify.commonlibs.mapper.UserMapper;
//import com.grocify.commonlibs.repository.AuthRepository;
//import java.util.Optional;
//import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.security.core.userdetails.UserDetails;
////import org.springframework.security.core.userdetails.UserDetailsService;
////import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
////@Component
//public class GrocifyUserDetailsService implements UserDetailsService {
//
//	@Autowired
//	private AuthRepository authRepository;
//
//	@Autowired
//	private UserMapper userMapper;
//
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//    	Optional<UserEntity> existingUser = authRepository.findByEmailId(username);
//
//		if (existingUser.isEmpty()) {
//			throw new UsernameNotFoundException("User with email id : " + username + " not found");
//		}
//
//		return userMapper.userEntityToUserDTO(existingUser.get());
//	}
//}
