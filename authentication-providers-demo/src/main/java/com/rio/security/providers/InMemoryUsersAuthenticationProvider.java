package com.rio.security.providers;

import java.util.Collections;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class InMemoryUsersAuthenticationProvider implements AuthenticationProvider {

  private final Map<String, String> usernamePasswordMap;

  public InMemoryUsersAuthenticationProvider() {
    this.usernamePasswordMap = Map.of(
        "inMemoryUsersAuthenticationProvider1@test.com",  "1234",
        "inMemoryUsersAuthenticationProvider2@test.com",  "12345",
        "inMemoryUsersAuthenticationProvider3@test.com",  "123456"
    );
  }

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    log.debug("Checking credentials using InMemoryUsersAuthenticationProvider");

    String email = authentication.getName();
    Object credentials = authentication.getCredentials();

    if (email != null && credentials != null) {
      String password = credentials.toString();

      if (usernamePasswordMap.containsKey(email) && usernamePasswordMap.get(email).equals(password)) {
        return new UsernamePasswordAuthenticationToken(email, password, Collections.emptyList());
      }
    }

    throw new BadCredentialsException("Credentials are invalid");
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
  }
}
