package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

import com.example.demo.service.JwtService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtService jwtService;

    public AuthController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest req) {

        if ("admin".equals(req.username()) && "pass".equals(req.password())) {
            return jwtService.generateToken(req.username());
        }

        throw new RuntimeException("Invalid login");
    }

    record LoginRequest(String username, String password) {}
}
