package com.rio.security;

import com.rio.entity.User;

public record UserPrinciple(String id, String email, User.Role role) {
}
