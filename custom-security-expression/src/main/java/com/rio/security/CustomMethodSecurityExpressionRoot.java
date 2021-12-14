package com.rio.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.WebSecurityExpressionRoot;

public class CustomMethodSecurityExpressionRoot extends WebSecurityExpressionRoot {

  public CustomMethodSecurityExpressionRoot(Authentication authentication, FilterInvocation fi) {
    super(authentication, fi);
  }

  public boolean isMember(String organizationId) {
    UserPrinciple userPrinciple = (UserPrinciple) this.getPrincipal();

    return userPrinciple.getOrganizationIds() != null && userPrinciple.getOrganizationIds().contains(organizationId);
  }
}
