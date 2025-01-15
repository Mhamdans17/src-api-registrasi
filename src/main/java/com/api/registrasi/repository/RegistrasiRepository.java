package com.api.registrasi.repository;

import com.api.registrasi.entity.RegistrasiDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrasiRepository extends JpaRepository<RegistrasiDataEntity, Integer> {
}
