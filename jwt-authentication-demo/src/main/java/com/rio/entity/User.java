package com.rio.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
public class User {

  @Id
  private String id;
  private String email;
  private String password;
  private Role role;

  public enum Role {
    FULL_ACCESS
  }

  public User(String email, String password, Role role) {
    this.email = email;
    this.password = password;
    this.role = role;
  }
}