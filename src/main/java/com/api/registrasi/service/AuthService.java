package com.api.registrasi.service;

import com.api.registrasi.entity.UserEntity;
import com.api.registrasi.repository.RoleRepository;
import com.api.registrasi.repository.UserRepository;
import com.api.registrasi.util.JwtUtil;
import com.api.registrasi.util.ValidationUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

import static com.api.registrasi.constants.Constants.INVALID_CRIDENTIAL;

@Slf4j
@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String login(String username, String password) {
        ValidationUtil.validateNotNullOrEmpty(username, "username");
        ValidationUtil.validateNotNullOrEmpty(password, "password");

        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException(INVALID_CRIDENTIAL));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException(INVALID_CRIDENTIAL);
        }

        Set<String> roles = user.getRoles().stream()
                .map(role -> role.getRoleName())
                .collect(Collectors.toSet());

        return JwtUtil.generateToken(username, roles);
    }

    public boolean validateToken(String token, String username) {
        return JwtUtil.validateToken(token, username);
    }
}