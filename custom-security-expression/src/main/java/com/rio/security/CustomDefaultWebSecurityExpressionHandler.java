package com.rio.security;

import org.springframework.security.access.expression.SecurityExpressionOperations;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;

public class CustomDefaultWebSecurityExpressionHandler extends DefaultWebSecurityExpressionHandler {

  @Override
  protected SecurityExpressionOperations createSecurityExpressionRoot(Authentication authentication, FilterInvocation fi) {
    return new CustomWebSecurityExpressionRoot(authentication, fi);
  }
}