package com.system.cardealership.authenticationservice.service

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.security.Key
import java.util.*


@Service
class JwtService {

    private val secret = "QYJQJPWSsz14wjgaWQg9ckJ8FzmGoKDI"

    fun extractUsername(token: String): String? {
        return extractClaim(token, Claims::getSubject)
    }

    fun <T> extractClaim(token: String, claimsResolver: (Claims) -> T): T {
        val claims = extractAllClaims(token)
        return claimsResolver.invoke(claims)
    }

    fun generateToken(userDetails: UserDetails): String {
        return generateToken(emptyMap(), userDetails)
    }

    fun generateToken(extraClaims: Map<String, Any>, userDetails: UserDetails): String {
        val currentTimeMillis = System.currentTimeMillis()
        val issuedAt = Date(currentTimeMillis)
        val expiration = Date(currentTimeMillis + 30 * 60 * 1000) // 30 minutes from current time

        return Jwts
            .builder()
            .setClaims(extraClaims)
            .setSubject(userDetails.username)
            .setIssuedAt(issuedAt)
            .setExpiration(expiration)
            .signWith(getSignInKey(), SignatureAlgorithm.HS256) // use your secret key
            .compact()
    }

    fun isTokenValid(token: String, userDetails: UserDetails): Boolean {
        val username = extractUsername(token)
        return username == userDetails.username && !isTokenExpired(token)
    }

    fun isTokenExpired(token: String): Boolean {
        val expiration = extractExpiration(token)
        return expiration.before(Date())
    }

    fun extractExpiration(token: String): Date {
        return extractClaim(token, Claims::getExpiration)
    }

    fun extractAllClaims(token: String): Claims {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .body
    }

    fun getSignInKey(): Key {
        // Convert the secret string to a byte array
        val secretBytes = secret.toByteArray()
        // Create a signing key using HMAC-SHA256 algorithm
        return Keys.hmacShaKeyFor(secretBytes)
    }
}
