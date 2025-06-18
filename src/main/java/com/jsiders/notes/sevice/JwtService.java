package com.jsiders.notes.sevice;

import com.jsiders.notes.dto.AuthResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class JwtService {

    private final String SECRET = "afafasfafafasfasfasfafacasdasfasxASFACASDFACASDFASFASFDAFASFASDAADSCSDFADCVSGCFVADXCcadwavfsfarvf";
    private final SecretKey secretKey = Keys.hmacShaKeyFor(SECRET.getBytes());

    private Logger logger = LoggerFactory.getLogger(JwtService.class);

    public AuthResponse generateToken(String username, Collection<? extends GrantedAuthority> authorities) {

        Date expiryTime = new Date(System.currentTimeMillis() + 1000 * 1000);


        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList()));
        String token = Jwts.builder()
                .issuer("Notes app")
                .claims(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(expiryTime)
                .signWith(secretKey)
                .id(UUID.randomUUID().toString())
                .compact();
        return AuthResponse.builder()
                .accessToken(token)
                .expiresIn(expiryTime)
                .type("Bearer")
                .build();

    }

    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    public boolean validateToken(String token, UserDetails userDetails) {

        boolean isTokenValid = false;

        try {
            String username = Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload()
                    .getSubject();
            if (username.equalsIgnoreCase(userDetails.getUsername())) {
                isTokenValid = true;
            }
        } catch (JwtException e) {
            logger.warn("Token expired: {}", e.getMessage());
        }
        return isTokenValid;
    }


    private Claims extractClaims(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }


}
