package com.rio.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class User {

  @Id
  private String id;
  private String email;
  private String password;
  private Role role;

  public enum Role {
    FULL_ACCESS
  }
}