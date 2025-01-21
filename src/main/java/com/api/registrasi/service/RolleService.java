package com.api.registrasi.service;

import com.api.registrasi.entity.RoleEntity;
import com.api.registrasi.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolleService {

    @Autowired
    private RoleRepository roleRepository;

    public RoleEntity createRole(String roleName) {
        // Cek apakah role sudah ada
        if (roleRepository.findByRoleName(roleName).isPresent()) {
            throw new IllegalArgumentException("Role dengan nama " + roleName + " sudah ada.");
        }

        // Membuat role baru
        RoleEntity role = new RoleEntity();
        role.setRoleName(roleName);

        // Simpan ke database
        return roleRepository.save(role);
    }

}
