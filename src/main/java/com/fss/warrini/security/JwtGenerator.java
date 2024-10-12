package com.fss.warrini.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtGenerator {
    public String generateJwt(Authentication auth) {
        String username = auth.getName();
        Date now = new Date();
        Date expiration = new Date(now.getTime() + SecurityConstants.EXPIRATION_DURATION);

        String token = Jwts.builder()
                .setIssuedAt(now)
                .setSubject(username)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, SecurityConstants.JWT_SECRET)
                .compact();
        return token;
    }

    public String getUsernameFromJwt(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SecurityConstants.JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public boolean validateJwt(String token) throws AuthenticationCredentialsNotFoundException {
        try{
            Jwts.parser().setSigningKey(SecurityConstants.JWT_SECRET).parseClaimsJws(token);
            return true;
        }catch(Exception e){
            return false;
        }
    }
}
