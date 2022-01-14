package com.rio.security;

import java.util.List;

public record UserPrinciple(
        String id,
        String email,
        List<String> organizationIds) {
}
