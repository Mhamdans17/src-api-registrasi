package com.api.registrasi.controller;

import com.api.registrasi.entity.RegistrasiDataEntity;
import com.api.registrasi.service.ServiceReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tokyodev/api")
public class ReportController {

    @Autowired
    private ServiceReport serviceReport;

    // Endpoint untuk mengambil laporan berdasarkan bulan dan tahun
    @GetMapping("/laporan")
    public ResponseEntity<List<RegistrasiDataEntity>> getReport(@RequestParam("bulan") String bulan, @RequestParam("tahun") String tahun) {
        return serviceReport.getReport(bulan, tahun); // Memanggil service untuk mendapatkan laporan
    }
}
