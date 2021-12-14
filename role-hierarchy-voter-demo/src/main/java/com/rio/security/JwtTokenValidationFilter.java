package com.rio.security;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.util.StringUtils.hasText;

import com.rio.entity.User;
import java.io.IOException;
import java.util.Collections;
import java.util.Optional;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

@RequiredArgsConstructor
public class JwtTokenValidationFilter extends GenericFilterBean {

  private final String secret;

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    logger.info("Validating jwt token...");

    obtainBearerToken((HttpServletRequest) servletRequest)
        .ifPresent(token -> JwtUtil.parseToken(token, secret)
            .ifPresent(user -> {
              var jwtAuthenticationToken = new JwtAuthenticationToken(user.getEmail(), "", Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRole().name())), token);
              SecurityContextHolder.getContext().setAuthentication(jwtAuthenticationToken);
            })
        );

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