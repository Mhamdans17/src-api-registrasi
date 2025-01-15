package com.api.registrasi.controller;

import com.api.registrasi.model.RequestRegistrasiDTO;
import com.api.registrasi.model.ResponseResgistrasiDTO;
import com.api.registrasi.service.ServiceRegistrasi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
