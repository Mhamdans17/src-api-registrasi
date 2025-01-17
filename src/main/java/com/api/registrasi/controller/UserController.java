package com.api.registrasi.controller;

import com.api.registrasi.entity.UserEntity;
import com.api.registrasi.model.RegisterUserDTO;
import com.api.registrasi.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/request/user")
    public ResponseEntity<UserEntity> createUser(@RequestBody RegisterUserDTO userRequest) {
        // Validasi dan panggil metode service
        UserEntity createdUser = userService.createUser(userRequest.getUserName(),
                userRequest.getPassword(),
                userRequest.getRoles());
        return ResponseEntity.ok(createdUser);
    }
}
