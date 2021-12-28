package com.rio.repository;

import com.mongodb.lang.NonNull;
import com.rio.entity.User;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

  Optional<User> findByEmail(@NonNull String email);

  boolean existsByEmail(@NonNull String email);
}
