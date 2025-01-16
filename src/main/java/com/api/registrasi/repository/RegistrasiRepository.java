package com.api.registrasi.repository;

import com.api.registrasi.entity.RegistrasiDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RegistrasiRepository extends JpaRepository<RegistrasiDataEntity, Integer> {
    List<RegistrasiDataEntity> findAllByNamaMurid(String namaMurid);
}
