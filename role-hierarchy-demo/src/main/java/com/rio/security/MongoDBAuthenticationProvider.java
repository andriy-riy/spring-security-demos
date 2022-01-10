package com.rio.security;

import com.rio.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.Optional;

@RequiredArgsConstructor
public class MongoDBAuthenticationProvider implements AuthenticationProvider {

    private final UserService userService;

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
                    if (password.equals(user.password())) {
                        var userPrinciple = new UserPrinciple(user.email(), user.role());
                        return Optional
                                .of(new UsernamePasswordAuthenticationToken(userPrinciple, password, Collections.singletonList(new SimpleGrantedAuthority(user.role().name()))));
                    }
                    return Optional.empty();
                })
                .orElseThrow(() -> new BadCredentialsException("Credentials are invalid"));
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
