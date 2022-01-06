package com.rio.service;

import com.rio.entity.User;
import com.rio.entity.User.Role;

import java.util.List;
import java.util.Optional;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final List<User> users = List.of(
            new User("admin@admin.com", "admin@admin", Role.ADMIN),
            new User("employee@employee.com", "employee@employee", Role.EMPLOYEE),
            new User("guest@guest.com", "guest@guest", Role.GUEST)
    );

    public Optional<User> getByEmail(@NonNull String email) {
        return users.stream()
                .filter(user -> user.email().equals(email))
                .findFirst();
    }
}
