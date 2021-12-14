package com.rio.entity;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {

  private String id;
  private String email;
  private String password;
  private List<String> organizationIds;
}