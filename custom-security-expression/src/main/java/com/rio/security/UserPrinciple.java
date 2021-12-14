package com.rio.security;

import java.util.List;
import lombok.Data;

@Data
public class UserPrinciple {

  private final String email;
  private final List<String> organizationIds;
}
