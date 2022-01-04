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
          .email("user1@email.com")
          .password("user1@user1")
          .organizationIds(List.of("1"))
          .build(),

      User.builder()
          .id("2")
          .email("user2@email.com")
          .password("user2@user2")
          .organizationIds(List.of("1"))
          .build(),

      User.builder()
          .id("3")
          .email("user3@email.com")
          .password("user3@user3")
          .organizationIds(List.of("2"))
          .build()
  );

  public Optional<User> getByEmail(@NonNull String email) {
    return users.stream()
        .filter(user -> user.getEmail().equals(email))
        .findFirst();
  }
}
