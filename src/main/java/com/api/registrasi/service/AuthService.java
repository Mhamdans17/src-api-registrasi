package com.api.registrasi.service;

import com.api.registrasi.entity.UserEntity;
import com.api.registrasi.repository.RolleRepository;
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
    private RolleRepository rolleRepository;

    // Method untuk login dan menghasilkan token JWT
    public String login(String username, String password) {
        // Validasi username dan password
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Invalid credentials"));

        // Validasi password (pastikan password yang dimasukkan sesuai dengan yang ada di DB)
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("Invalid credentials");
        }

        // Ambil roles dari database dan simpan dalam set
        Set<String> roles = user.getRoles().stream()
                .map(role -> role.getRoleName())  // Ambil nama role
                .collect(Collectors.toSet());

        // Generate token dengan roles
        return JwtUtil.generateToken(username, roles);
    }

    // Method untuk validasi token JWT
    public boolean validateToken(String token, String username) {
        return JwtUtil.validateToken(token, username);  // Validasi token
    }
}