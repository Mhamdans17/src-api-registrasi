package com.api.registrasi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class LoginController {
    @GetMapping("/login")
    public String login() {
        return "login"; // Nama file template di folder resources/templates/login.html
    }
}
