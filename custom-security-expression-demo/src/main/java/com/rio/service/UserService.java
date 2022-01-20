package com.rio.service;

import com.rio.entity.User;

import java.util.List;
import java.util.Optional;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final List<User> users = List.of(
            new User("1", "omelnyk@jappware.com", "omelnyk@omelnyk", List.of("1")),
            new User("2", "ariy@jappware.com", "ariy@ariy", List.of("1")),
            new User("3", "omilovskyi@jappware.com", "omilovskyi@omilovskyi", List.of("1")),
            new User("4", "якийсь_лєвий_чувак@email.com", "user@user", List.of("2"))
    );

    public Optional<User> getByEmail(@NonNull String email) {
        return users.stream()
                .filter(user -> user.email().equals(email))
                .findFirst();
    }
}
