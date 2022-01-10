package com.rio.service;

import com.rio.entity.User;

import java.util.List;
import java.util.Optional;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final List<User> users = List.of(
            new User("1", "user1@email.com", "user1@user1", List.of("1")),
            new User("2", "user2@email.com", "user2@user2", List.of("1")),
            new User("3", "user3@email.com", "user3@user3", List.of("2"))
    );

    public Optional<User> getByEmail(@NonNull String email) {
        return users.stream()
                .filter(user -> user.email().equals(email))
                .findFirst();
    }
}
