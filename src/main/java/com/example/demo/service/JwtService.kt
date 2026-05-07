package com.example.demo.service

import io.jsonwebtoken.Claims
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Service
import java.util.*
import javax.crypto.SecretKey

@Service
open class JwtService {
    private val key: SecretKey = Keys.hmacShaKeyFor(SECRET.toByteArray())

    fun generateToken(username: String?): String? {
        return Jwts.builder()
            .subject(username)
            .issuedAt(Date())
            .expiration(Date(System.currentTimeMillis() + EXPIRATION))
            .signWith(key)
            .compact()
    }

    fun extractUsername(token: String?): String {
        return parse(token)?.subject ?: throw RuntimeException("token not found")
    }

    fun validate(token: String?): Boolean {
        try {
            parse(token)
            return true
        } catch (e: JwtException) {
            return false
        }
    }

    private fun parse(token: String?): Claims? {
        return Jwts.parser()
            .verifyWith(key)
            .build()
            .parseSignedClaims(token)
            .getPayload()
    }

    companion object {
        private const val SECRET = "supersecretkeysupersecretkeysupersecretkey1234"
        private val EXPIRATION = (1000 * 60 * 60 // 1 time
                ).toLong()
    }
}
