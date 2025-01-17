package com.api.registrasi.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginResponseDTO {
    private String token;

    // Constructor yang benar untuk inisialisasi token
    public LoginResponseDTO(String token) {
        this.token = token;
    }
}