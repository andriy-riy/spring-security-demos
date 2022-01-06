package com.rio.service;

import com.rio.entity.User;
import com.rio.entity.User.Role;
import com.rio.repository.UserRepository;

import java.util.Optional;
import javax.annotation.PostConstruct;

import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @PostConstruct
    public void init() {
        if (!userRepository.existsByEmail("mongodb1@test.com")) {
            userRepository.insert(new User("mongodb1@test.com", "1234", Role.FULL_ACCESS));
        }

        if (!userRepository.existsByEmail("mongodb2@test.com")) {
            userRepository.insert(new User("mongodb2@test.com", "12345", Role.FULL_ACCESS));
        }

        if (!userRepository.existsByEmail("mongodb3@test.com")) {
            userRepository.insert(new User("mongodb3@test.com", "123456", Role.FULL_ACCESS));
        }
    }

    public Optional<User> getByEmail(@NonNull String email) {
        return userRepository.findByEmail(email);
    }
}
