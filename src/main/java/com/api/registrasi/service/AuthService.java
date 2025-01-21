package com.api.registrasi.service;

import com.api.registrasi.entity.UserEntity;
import com.api.registrasi.repository.RoleRepository;
import com.api.registrasi.repository.UserRepository;
import com.api.registrasi.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository rolleRepository;

    public String login(String username, String password) {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Invalid credentials"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("Invalid credentials");
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