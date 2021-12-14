package com.rio.security;

import com.rio.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtUtil {

  public static Optional<UserPrinciple> parseToken(String token, String secret) {
    try {
      Claims body = Jwts.parser()
          .setSigningKey(secret)
          .parseClaimsJws(token)
          .getBody();

      UserPrinciple userPrinciple = new UserPrinciple(body.get("email").toString(), (List<String>) body.get("organizationIds"));

      return Optional.of(userPrinciple);

    } catch (JwtException | ClassCastException e) {
      log.error("Issue happened during parsing token", e);
      return Optional.empty();
    }
  }

  public static String generateToken(User user, String secret) {
    return Jwts.builder()
        .claim("email", user.getEmail())
        .claim("organizationIds", user.getOrganizationIds())
        .signWith(SignatureAlgorithm.HS512, secret)
        .compact();
  }
}
