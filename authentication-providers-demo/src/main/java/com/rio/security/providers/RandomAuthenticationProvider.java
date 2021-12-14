package com.rio.security.providers;

import java.util.Collections;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RandomAuthenticationProvider implements AuthenticationProvider {

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    log.debug("Checking credentials using RandomAuthenticationProvider");

    if (randomBoolean()) {
      return new UsernamePasswordAuthenticationToken("authenticated user", "without password", Collections.emptyList());
    }

    throw new BadCredentialsException("Credentials are invalid");
  }

  public boolean randomBoolean(){
    return Math.random() < 0.5;
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
  }
}
