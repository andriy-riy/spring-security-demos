package com.rio.security;

import com.rio.service.UserService;
import java.util.Collections;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

@RequiredArgsConstructor
public class JwtAuthenticationProvider implements AuthenticationProvider {

  private final UserService userService;
  private final String secret;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    String email = authentication.getName();
    Object credentials = authentication.getCredentials();

    if (email == null || credentials == null) {
      throw new BadCredentialsException("Credentials are invalid");
    }

    String password = credentials.toString();

    return userService.getByEmail(email)
        .flatMap(user -> {
          if (password.equals(user.getPassword())) {
            return Optional
                .of(new JwtAuthenticationToken(email, password, Collections.emptyList(), JwtUtil.generateToken(user, secret)));
          }
          return Optional.empty();
        })
        .orElseThrow(() -> new BadCredentialsException("Credentials are invalid"));
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return JwtAuthenticationToken.class.isAssignableFrom(authentication);
  }
}
