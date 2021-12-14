package com.rio.service;

import com.rio.entity.User;
import com.rio.repository.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  public Optional<User> getByEmail(@NonNull String email) {
    return userRepository.findByEmail(email);
  }
}
