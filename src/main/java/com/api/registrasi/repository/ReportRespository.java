package com.api.registrasi.repository;

import com.api.registrasi.entity.RegistrasiDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ReportRespository extends JpaRepository<RegistrasiDataEntity, Integer> {
    @Query("SELECT r FROM RegistrasiDataEntity r WHERE CAST(r.tanggalDaftar AS date) BETWEEN :startDate AND :endDate")
    List<RegistrasiDataEntity> findByTanggalDaftarBetween(LocalDate startDate, LocalDate endDate);
}
