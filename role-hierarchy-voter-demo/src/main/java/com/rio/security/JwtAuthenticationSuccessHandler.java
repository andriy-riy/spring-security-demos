package com.rio.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Duration;

@RequiredArgsConstructor
public class JwtAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

  private final ObjectMapper objectMapper;
  private final String secret;
  private final Duration tokenExpiration;

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
    UserPrinciple principal = (UserPrinciple) authentication.getPrincipal();

    String token = JwtUtil.generateToken(principal, secret, tokenExpiration);
    JwtTokenResponse jwtTokenResponse = new JwtTokenResponse(token, tokenExpiration.toSeconds());
    String responseBody = objectMapper.writeValueAsString(jwtTokenResponse);

    response.setStatus(HttpServletResponse.SC_OK);
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");
    response.getWriter().write(responseBody);
    response.getWriter().flush();
  }
}