package com.api.registrasi.controller;

import com.api.registrasi.entity.RegistrasiDataEntity;
import com.api.registrasi.model.RequestRegistrasiDTO;
import com.api.registrasi.model.ResponseResgistrasiDTO;
import com.api.registrasi.service.ServiceRegistrasi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tokyodev/api")
public class RegistrasiController {

    @Autowired
    ServiceRegistrasi serviceRegistrasi;

    @PostMapping("/registrasi")
    public ResponseEntity<ResponseResgistrasiDTO> registerMuridWali(
            @Validated @RequestBody RequestRegistrasiDTO requestDTO) {
        ResponseResgistrasiDTO response = serviceRegistrasi.resResgistrasi(requestDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    public ResponseEntity<List<RegistrasiDataEntity>> getDataByNamaAndUmur(
            @RequestParam String namaMurid,
            @RequestParam String umur) {
        return serviceRegistrasi.getDataMurid(namaMurid, umur);
    }


}
