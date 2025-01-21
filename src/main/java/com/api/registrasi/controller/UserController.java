package com.api.registrasi.controller;

import com.api.registrasi.entity.UserEntity;
import com.api.registrasi.model.RequestRegisterUserDTO;
import com.api.registrasi.model.UserDTO;
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
    public ResponseEntity<UserDTO> createUser(@RequestBody RequestRegisterUserDTO userRequest) {
        // Validasi dan panggil metode service
        UserDTO createdUser = userService.createUser(userRequest.getUserName(),
                userRequest.getPassword(),
                userRequest.getRoles());
        return ResponseEntity.ok(createdUser);
    }
}
