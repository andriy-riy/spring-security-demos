package com.rio.security;

import com.rio.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.Optional;

@Slf4j
public class JwtUtil {

    public static Optional<UserPrinciple> parseToken(String token, String secret) {
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();

            return Optional.of(new UserPrinciple(body.getSubject(), User.Role.valueOf(body.get("role").toString())));
        } catch (JwtException | ClassCastException e) {
            log.error("Issue happened during parsing token", e);
            return Optional.empty();
        }
    }

    public static String generateToken(UserPrinciple userPrinciple, String secret, Duration tokenExpiration) {
        return Jwts.builder()
                .claim("email", userPrinciple.email())
                .claim("role", userPrinciple.role())
                .signWith(SignatureAlgorithm.HS512, secret)
                .setExpiration(Date.from(LocalDateTime.now().plus(tokenExpiration).toInstant(ZoneOffset.UTC)))
                .compact();
    }
}
