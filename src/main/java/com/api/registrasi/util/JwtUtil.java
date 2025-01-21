package com.api.registrasi.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Set;

public class JwtUtil {

    // Kunci rahasia yang aman untuk HS256
    private static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor("my-very-strong-and-long-secret-key-for-jwt".getBytes());
    private static final long EXPIRATION_TIME = 86400000; // 1 day in milliseconds

    // Generate JWT Token
    public static String generateToken(String username, Set<String> roles) {
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }

        return Jwts.builder()
                .setSubject(username)  // Set subject (username)
                .claim("roles", roles) // Set roles claim
                .setIssuedAt(new Date())  // Set issued date
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))  // Set expiration date
                .signWith(SECRET_KEY)  // Sign token with the secret key
                .compact();  // Return the compacted token
    }

    // Validate JWT Token
    public static boolean validateToken(String token, String username) {
        String tokenUsername = extractUsername(token);  // Extract username from token
        return tokenUsername.equals(username) && !isTokenExpired(token);  // Validate token and username
    }

    // Extract Username from Token
    public static String extractUsername(String token) {
        return extractClaims(token).getSubject();  // Get subject (username) from claims
    }

    // Check if Token is Expired
    public static boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());  // Check if token is expired
    }

    // Extract All Claims from Token
    public static Claims extractClaims(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(SECRET_KEY)  // Set the secret key for signing
                    .parseClaimsJws(token)  // Parse JWT
                    .getBody();  // Get claims from the token
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid token", e);  // Throw exception if token is invalid
        }
    }
}
