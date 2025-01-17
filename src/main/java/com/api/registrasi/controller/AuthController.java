package com.api.registrasi.controller;

import com.api.registrasi.model.LoginRequestDTO;
import com.api.registrasi.model.LoginResponseDTO;
import com.api.registrasi.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    // Endpoint untuk login
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequest) {
        String tokenBarer = authService.login(loginRequest.getUsername(), loginRequest.getPassword());
        return ResponseEntity.ok(new LoginResponseDTO(tokenBarer));
    }
}
