package com.api.registrasi.repository;

import com.api.registrasi.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolleRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByRoleName(String roleName);
}
