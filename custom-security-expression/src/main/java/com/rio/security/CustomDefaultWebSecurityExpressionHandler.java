package com.rio.security;

import org.springframework.security.access.expression.AbstractSecurityExpressionHandler;
import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.security.access.expression.SecurityExpressionOperations;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.util.Assert;

public class CustomDefaultWebSecurityExpressionHandler extends
    AbstractSecurityExpressionHandler<FilterInvocation> implements
    SecurityExpressionHandler<FilterInvocation> {

  private AuthenticationTrustResolver trustResolver = new AuthenticationTrustResolverImpl();
  private String defaultRolePrefix = "ROLE_";

  @Override
  protected SecurityExpressionOperations createSecurityExpressionRoot(
      Authentication authentication, FilterInvocation fi) {
    CustomMethodSecurityExpressionRoot root = new CustomMethodSecurityExpressionRoot(authentication, fi);
    root.setPermissionEvaluator(getPermissionEvaluator());
    root.setTrustResolver(trustResolver);
    root.setRoleHierarchy(getRoleHierarchy());
    root.setDefaultRolePrefix(this.defaultRolePrefix);
    return root;
  }

  public void setTrustResolver(AuthenticationTrustResolver trustResolver) {
    Assert.notNull(trustResolver, "trustResolver cannot be null");
    this.trustResolver = trustResolver;
  }

  public void setDefaultRolePrefix(String defaultRolePrefix) {
    this.defaultRolePrefix = defaultRolePrefix;
  }
}