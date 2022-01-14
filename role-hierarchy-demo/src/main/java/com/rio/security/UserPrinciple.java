package com.rio.security;

import com.rio.entity.User;

public record UserPrinciple(
        String email,
        User.Role role) {
}
