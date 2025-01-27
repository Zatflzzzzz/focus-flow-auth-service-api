package org.myProject.focus_flow_auth_service_api.api.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.myProject.focus_flow_auth_service_api.api.dto.UserDetailsDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${jwt.token.secret}")
    private String secret;

    @Value("${jwt.token.expirationMs}")
    private int expirationMs;

    public String generateToken(Authentication authentication) {

        UserDetailsDto userDetails = (UserDetailsDto) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .claim("id", userDetails.getId())
                .claim("email", userDetails.getEmail())
                .claim("username", userDetails.getUsername())
                .claim("password", userDetails.getPassword())
                .claim("role", userDetails.getRole().name())
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date().getTime() + expirationMs)))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public Claims getAllClaimsFromJwtToken(String token) {

        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    public String getNameFromJwtToken(String token) {
        return getAllClaimsFromJwtToken(token).getSubject();
    }

    public String getEmailFromJwtToken(String token) {
        return getAllClaimsFromJwtToken(token).get("email", String.class);
    }

    public String getUsernameFromJwtToken(String token) {
        return getAllClaimsFromJwtToken(token).get("username", String.class);
    }

    public String getPasswordFromJwtToken(String token) {
        return getAllClaimsFromJwtToken(token).get("password", String.class);
    }

    public String getRoleFromJwtToken(String token) {
        return getAllClaimsFromJwtToken(token).get("role", String.class);
    }

    public Long getIdFromJwtToken(String token) {
        return getAllClaimsFromJwtToken(token).get("id", Long.class);
    }
}
