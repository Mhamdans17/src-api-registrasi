package com.api.registrasi.service;

import com.api.registrasi.entity.RoleEntity;
import com.api.registrasi.repository.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RolleService {

    @Autowired
    private RoleRepository roleRepository;

    public RoleEntity createRole(String roleName) {
        // Cek apakah role sudah ada
        if (roleRepository.findByRoleName(roleName).isPresent()) {
            throw new IllegalArgumentException("Role dengan nama " + roleName + " sudah ada.");
        }

        RoleEntity role = new RoleEntity();
        role.setRoleName(roleName);

        log.info("Data role berhasil ditambah: {}", role);

        return roleRepository.save(role);
    }

}
