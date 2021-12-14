package com.rio.security;

import java.util.Collection;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class JwtAuthenticationToken extends UsernamePasswordAuthenticationToken {

  @Getter
  @Setter
  private String token;

  public JwtAuthenticationToken(Object principal, Object credentials) {
    super(principal, credentials);
  }

  public JwtAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities, String token) {
    super(principal, credentials, authorities);
    this.token = token;
  }
}
