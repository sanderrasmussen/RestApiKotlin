package com.example.demo.controller

import com.example.demo.service.JwtService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(private val jwtService: JwtService) {
    @PostMapping("/login")
    fun login(@RequestBody req: LoginRequest): LoginResponse {
        if ("admin" == req.username && "pass" == req.password) {
            val token = jwtService.generateToken(req.username)
            return LoginResponse(token, "admin")
        }

        throw RuntimeException("Invalid login")
    }

    data class LoginRequest(
        val username: String,
        val password: String
    )

    data class LoginResponse(
        val token: String,
        val username: String
    )
}
