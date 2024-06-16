package com.loginManagement.lms.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
public class JWTTokenProvider {
    @Value("${app.jwt-secret}")
    private String secretKey;
    @Value("{app.jwt-expiration-millisecond}")
    private String expirationTime;

    public String generateToken(Authentication authentication) {
        Date expirationDate = new Date(new Date().getTime() + expirationTime);
        return Jwts.builder().setSubject(authentication.getName()).
                setIssuedAt(new Date()).
                setExpiration(expirationDate).
                signWith(getKey()).compact();
    }

    private Key getKey() {
        return Keys.hmacShaKeyFor(
                Decoders.BASE64.decode(secretKey)
        );
    }

    //public get
}
