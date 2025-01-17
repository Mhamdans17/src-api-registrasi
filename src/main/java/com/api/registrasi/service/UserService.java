package com.api.registrasi.service;

import com.api.registrasi.entity.RoleEntity;
import com.api.registrasi.entity.UserEntity;
import com.api.registrasi.repository.RolleRepository;
import com.api.registrasi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RolleRepository rolleRepository;

    public UserEntity createUser(String username, String password, Set<String> roleNames) {
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }

        // Cek apakah user sudah ada
        if (userRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("Username Already Exists");
        }

        Set<RoleEntity> roleEntities = new HashSet<>();

        // Menambahkan role berdasarkan roleNames
        for (String roleName : roleNames) {
            RoleEntity role = rolleRepository.findByRoleName(roleName)
                    .orElseThrow(() -> new IllegalArgumentException("Role " + roleName + " tidak ditemukan."));
            roleEntities.add(role);
        }

        // Membuat dan menyimpan user
        UserEntity user = new UserEntity();
        user.setUsername(username);
        // Encode password
        String encodedPassword = passwordEncoder.encode(password);
        user.setPassword(encodedPassword);
        user.setRoles(roleEntities);

        return userRepository.save(user);
    }

    // Method tambahan untuk menampilkan password dengan format tertentu (misalnya, mask sebagian)
    public String getMaskedPassword(String rawPassword) {
        String encodedPassword = passwordEncoder.encode(rawPassword);
        return encodedPassword.substring(0, Math.min(3, encodedPassword.length())) + "XXXX";
    }
}

