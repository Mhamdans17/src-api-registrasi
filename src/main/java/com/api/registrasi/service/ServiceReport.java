package com.api.registrasi.service;

import com.api.registrasi.entity.RegistrasiDataEntity;
import com.api.registrasi.repository.ReportRespository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@Slf4j
@Service
public class ServiceReport {
    @Autowired
    private ReportRespository reportRespository;

    // Method untuk mendapatkan laporan berdasarkan rentang tanggal awal dan akhir bulan
    public ResponseEntity<List<RegistrasiDataEntity>> getReport(String bulan, String tahun) {
        // Parsing bulan dan tahun menjadi integer
        int month = Integer.parseInt(bulan);
        int year = Integer.parseInt(tahun);

        // Menghitung tanggal pertama dan terakhir di bulan yang dipilih
        YearMonth yearMonth = YearMonth.of(year, month);
        LocalDate startDate = yearMonth.atDay(1); // Tanggal pertama di bulan
        LocalDate endDate = yearMonth.atEndOfMonth(); // Tanggal terakhir di bulan

        // Mengambil data dalam rentang tanggal
        List<RegistrasiDataEntity> reportSiswa = reportRespository.findByTanggalDaftarBetween(startDate, endDate);

        // Jika tidak ada data ditemukan
        if (reportSiswa.isEmpty()) {
            throw new IllegalArgumentException("Data untuk bulan " + bulan + " tahun " + tahun + " tidak ditemukan.");
        }

        log.info("Data Report bulan: {} {}", bulan, reportSiswa);

        return ResponseEntity.ok(reportSiswa);
    }
}
