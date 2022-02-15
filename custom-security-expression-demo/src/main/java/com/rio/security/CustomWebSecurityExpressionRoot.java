package com.rio.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.WebSecurityExpressionRoot;

public class CustomWebSecurityExpressionRoot extends WebSecurityExpressionRoot {

    public CustomWebSecurityExpressionRoot(Authentication authentication, FilterInvocation fi) {
        super(authentication, fi);
    }

    public boolean isOrganizationMember(String organizationId) {
        if (this.authentication.isAuthenticated()) {
            if (this.getPrincipal() instanceof UserPrinciple userPrinciple) {
                return userPrinciple.organizationIds() != null && userPrinciple.organizationIds().contains(organizationId);
            }
        }

        return false;
    }
}
