//package com.grocify.commonlibs.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
//
////@Configuration
//
//public class SecurityConfig {
//
//	@Autowired
//	private JWTAuthenticationFilter authFilter;
//
//	@Bean
//	public UserDetailsService userDetailsService() {
//   		 return new GrocifyUserDetailsService(); // Ensure UserInfoService implements UserDetailsService
//	}
//
//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//		http
//				.csrf(csrf -> csrf.disable()) // Disable CSRF for stateless APIs
//				.authorizeHttpRequests(auth -> auth
//						.requestMatchers("/auth/**").permitAll()
//						.anyRequest().authenticated()
//				)
//				.sessionManagement(sess -> sess
//						.sessionCreationPolicy(SessionCreationPolicy.STATELESS) // No sessions
//				)
//				.authenticationProvider(authenticationProvider()) // Custom authentication provider
//				.addFilterBefore(authFilter, BasicAuthenticationFilter.class); // Add JWT filter
//
//		return http.build();
//	}
//
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder(); // Password encoding
//	}
//
//	@Bean
//	public AuthenticationProvider authenticationProvider() {
//		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//		authenticationProvider.setUserDetailsService(userDetailsService());
//		authenticationProvider.setPasswordEncoder(passwordEncoder());
//		return authenticationProvider;
//	}
//
//	@Bean
//	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
//		return config.getAuthenticationManager();
//	}
//
//}
