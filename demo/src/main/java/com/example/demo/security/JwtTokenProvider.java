package com.example.demo.security;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtTokenProvider {
    @Value("${jwt.secret:change-this-secret-at-least-32-chars-please-123456}")
    private String jwtSecret;

    @Value("${jwt.expiration:3600000}")
    private long jwtExpirationInMs;

    private SecretKey key() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }

    public String generarToken(String username) {
        Date now = new Date();
        return Jwts.builder()
                .subject(username)
                .issuedAt(now)
                .expiration(new Date(now.getTime() + jwtExpirationInMs))
                .signWith(key(), Jwts.SIG.HS512)
                .compact();
    }

    public String getUsernameFromJWT(String token) {
        return Jwts.parser().verifyWith(key()).build()
                .parseSignedClaims(token).getPayload().getSubject();
    }

    public boolean validarToken(String token) {
        try { Jwts.parser().verifyWith(key()).build().parse(token); return true; }
        catch (JwtException | IllegalArgumentException e) { return false; }
    }
}
