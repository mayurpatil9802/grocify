

package com.grocify.commonlibs.security;

import com.grocify.commonlibs.entity.UserEntity;
import com.grocify.commonlibs.repository.AuthRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class GrocifyUserDetailsService implements UserDetailsService {

	@Autowired
	private AuthRepository authRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<UserEntity> existingUser = authRepository.findByEmailId(username);

		if (existingUser.isEmpty()) {
			throw new UsernameNotFoundException("User with email id : " + username + " not found");
		}

		UserEntity userEntity = existingUser.get();

		// ✅ Correctly mapping `UserEntity` to `UserDetails`
		return User.builder()
				.username(userEntity.getEmailId())  // Assuming email is used as the username
				.password(userEntity.getPassword()) // Ensure password is properly stored (BCrypt)
				.roles(userEntity.getRole().name())  // Assuming roles are stored correctly
				.build();
	}
}
