package com.rio.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {

  private String email;
  private String password;
  private Role role;

  public enum Role {
    ADMIN, EMPLOYEE, GUEST
  }
}