package com.gl.utilitaire;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {
    private final String secret = "isi2026keurmassarmgenielogicieletudiants";
    private final Key key = Keys.hmacShaKeyFor(secret.getBytes());
    private final long EXPIRATION_TIME = 864000000;
    private final long REFRESH_TIME = 604800000;

    public String generateJwtToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime()+EXPIRATION_TIME))
                .signWith(key)
                .compact();
    }
    public String getUsernameFromJwtToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(authToken);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
