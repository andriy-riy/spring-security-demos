package com.rio.security;

import com.rio.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtUtil {

  public static Optional<User> parseToken(String token, String secret) {
    try {
      Claims body = Jwts.parser()
          .setSigningKey(secret)
          .parseClaimsJws(token)
          .getBody();

      User user = User.builder()
          .email(body.get("email").toString())
          .role(User.Role.valueOf(body.get("role").toString()))
          .build();

      return Optional.of(user);

    } catch (JwtException | ClassCastException e) {
      log.error("Issue happened during parsing token", e);
      return Optional.empty();
    }
  }

  public static String generateToken(User user, String secret) {
    return Jwts.builder()
        .claim("email", user.getEmail())
        .claim("role", user.getRole())
        .signWith(SignatureAlgorithm.HS512, secret)
        .compact();
  }
}
