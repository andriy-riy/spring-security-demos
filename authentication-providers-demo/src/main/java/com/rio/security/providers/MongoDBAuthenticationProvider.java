package com.rio.security.providers;

import com.rio.entity.User;
import com.rio.repository.UserRepository;

import java.util.Collections;
import java.util.Optional;
import javax.annotation.PostConstruct;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MongoDBAuthenticationProvider implements AuthenticationProvider {

    private final UserRepository userRepository;

    @PostConstruct
    public void init() {
        if (!userRepository.existsByEmail("mongodb1@test.com")) {
            userRepository.insert(new User("mongodb1@test.com", "1234"));
        }

        if (!userRepository.existsByEmail("mongodb2@test.com")) {
            userRepository.insert(new User("mongodb2@test.com", "12345"));
        }

        if (!userRepository.existsByEmail("mongodb3@test.com")) {
            userRepository.insert(new User("mongodb3@test.com", "123456"));
        }
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.debug("Checking credentials using MongoDBAuthenticationProvider");

        String email = authentication.getName();
        Object credentials = authentication.getCredentials();

        if (email != null && credentials != null) {
            String password = credentials.toString();

            return userRepository.findByEmail(email)
                    .flatMap(user -> {
                        if (password.equals(user.getPassword())) {
                            return Optional.of(new UsernamePasswordAuthenticationToken(email, password, Collections.emptyList()));
                        }

                        return Optional.empty();
                    })
                    .orElseThrow(() -> new BadCredentialsException("Credentials are invalid"));
        }

        throw new BadCredentialsException("Credentials are invalid");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
