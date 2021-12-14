package com.rio.service;

import com.rio.entity.User;
import java.util.List;
import java.util.Optional;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final List<User> users = List.of(
      User.builder()
          .id("1")
          .email("admin@email.com")
          .password("admin@admin")
          .organizationIds(List.of("1"))
          .build(),

      User.builder()
          .id("2")
          .email("employee@employee.com")
          .password("employee@employee")
          .organizationIds(List.of("1"))
          .build(),

      User.builder()
          .id("3")
          .email("guest@guest.com")
          .password("guest@guest")
          .organizationIds(List.of("2"))
          .build()
  );

  public Optional<User> getByEmail(@NonNull String email) {
    return users.stream()
        .filter(user -> user.getEmail().equals(email))
        .findFirst();
  }
}
