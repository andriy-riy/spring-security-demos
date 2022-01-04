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
      User.builder()
      .role(Role.ADMIN)
      .email("admin@admin.com")
      .password("admin@admin")
      .build(),

      User.builder()
          .role(Role.EMPLOYEE)
          .email("employee@employee.com")
          .password("employee@employee")
          .build(),

      User.builder()
          .role(Role.GUEST)
          .email("guest@guest.com")
          .password("guest@guest")
          .build()
  );

  public Optional<User> getByEmail(@NonNull String email) {
    return users.stream()
        .filter(user -> user.getEmail().equals(email))
        .findFirst();
  }
}
