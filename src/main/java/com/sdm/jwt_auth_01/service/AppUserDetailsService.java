package com.sdm.jwt_auth_01.service;

import com.sdm.jwt_auth_01.entity.UserEntity;
import com.sdm.jwt_auth_01.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class AppUserDetailsService implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(AppUserDetailsService.class);
    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        logger.info("Attempting to load by email: {}", email);
        UserEntity existingUser = userRepository.findByEmail(email)
                .orElseThrow(() -> {
                    logger.warn("User with email '{}' not found", email);
                    return new UsernameNotFoundException("Email not found for the email: " + email);
                });

        logger.info("User found: {}.", existingUser.getEmail());

        return new User(existingUser.getEmail(), existingUser.getPassword(), new ArrayList<>());
    }
}
