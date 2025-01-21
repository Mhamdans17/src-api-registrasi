package com.api.registrasi.controller;

import com.api.registrasi.entity.RoleEntity;
import com.api.registrasi.service.RolleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RolleController {

    private final RolleService rolleService;
    public RolleController(RolleService rolleService) {
        this.rolleService = rolleService;
    }

    @PostMapping("/create/roll")
    public ResponseEntity<RoleEntity> createRolle(@RequestParam String rolle) {
        RoleEntity role = rolleService.createRole(String.valueOf(rolle));
        return ResponseEntity.ok(role);

    }

}
