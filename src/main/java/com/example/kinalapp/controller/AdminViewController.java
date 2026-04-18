package com.example.kinalapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminViewController {

    @GetMapping("/admin-usuarios")
    public String adminUsuarios() {
        return "admin-usuario";
    }
}