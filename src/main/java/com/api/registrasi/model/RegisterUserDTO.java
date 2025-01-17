package com.api.registrasi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserDTO {

    private String userName;
    private String password;
    private Set<String> roles;
}

