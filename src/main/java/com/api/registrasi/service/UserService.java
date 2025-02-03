package com.api.registrasi.service;

import com.api.registrasi.entity.RoleEntity;
import com.api.registrasi.entity.UserEntity;
import com.api.registrasi.model.UserDTO;
import com.api.registrasi.repository.RoleRepository;
import com.api.registrasi.repository.UserRepository;
import com.api.registrasi.util.ValidationUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository rolleRepository;

    public UserDTO createUser(String username, String password, Set<String> roleNames) {

        log.info("Requests Body Registrasi User: userName={}, roleNames={}", username, roleNames);

        ValidationUtil.validateNotNullOrEmpty(username, "Username");
        ValidationUtil.validateNotNullOrEmpty(password, "Password");

        // Cek apakah user sudah ada
        if (userRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("Username Already Exists");
        }

        Set<RoleEntity> roleEntities = new HashSet<>();

        // Menambahkan role berdasarkan roleNames
        for (String roleName : roleNames) {
            RoleEntity role = rolleRepository.findByRoleName(roleName)
                    .orElseThrow(() -> {
                        log.error("Role {} tidak ditemukan.", roleName);
                        return new IllegalArgumentException("Role " + roleName + " tidak ditemukan.");
                    });
            roleEntities.add(role);
        }

        UserEntity user = new UserEntity();
        user.setUsername(username);
        String encodedPassword = passwordEncoder.encode(password);
        user.setPassword(encodedPassword);
        user.setRoles(roleEntities);

        userRepository.save(user);
        log.info("User berhasil disimpan dengan username={}", username);

        // Map roles to role names
        Set<String> roleNamesResponse = new HashSet<>();
        for (RoleEntity role : roleEntities) {
            roleNamesResponse.add(role.getRoleName());
        }

        UserDTO response = new UserDTO(user.getUsername(), roleNamesResponse);
        log.info("Berhasil membuat user. Data response: {}", response);

        return response;
    }

    // Method tambahan untuk menampilkan password dengan format tertentu (misalnya, mask sebagian)
    public String getMaskedPassword(String rawPassword) {
        String encodedPassword = passwordEncoder.encode(rawPassword);
        return encodedPassword.substring(0, Math.min(3, encodedPassword.length())) + "XXXX";
    }
}

