package com.rio.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
public class JwtUtil {

    public static Optional<UserPrinciple> parseToken(String token, String secret) {
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();

            return Optional.of(new UserPrinciple(body.get("id").toString(), body.getSubject(), (List<String>) body.get("organizationIds")));
        } catch (JwtException | ClassCastException e) {
            log.error("Issue happened during parsing token", e);
            return Optional.empty();
        }
    }

    public static String generateToken(UserPrinciple userPrinciple, String secret, Duration tokenExpiration) {
        return Jwts.builder()
                .claim("id", userPrinciple.id())
                .claim("email", userPrinciple.email())
                .claim("organizationIds", userPrinciple.organizationIds())
                .signWith(SignatureAlgorithm.HS512, secret)
                .setExpiration(Date.from(LocalDateTime.now().plus(tokenExpiration).toInstant(ZoneOffset.UTC)))
                .compact();
    }
}
