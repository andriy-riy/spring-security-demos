package com.rio.security;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.util.StringUtils.hasText;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

@RequiredArgsConstructor
public class JwtBearerTokenFilter extends GenericFilterBean {

  private final String secret;

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    logger.info("Validating jwt token...");

    obtainBearerToken((HttpServletRequest) servletRequest)
            .flatMap(token -> JwtUtil.parseToken(token, secret))
            .ifPresent(userPrinciple -> SecurityContextHolder.getContext()
                    .setAuthentication(new UsernamePasswordAuthenticationToken(userPrinciple, "", Collections.singletonList(new SimpleGrantedAuthority(userPrinciple.getRole().name())))));

    filterChain.doFilter(servletRequest, servletResponse);
  }

  private Optional<String> obtainBearerToken(HttpServletRequest request) {
    String bearer = request.getHeader(AUTHORIZATION);

    if (hasText(bearer) && bearer.startsWith("Bearer ")) {
      return Optional.of(bearer.substring(7));
    }

    return Optional.empty();
  }
}