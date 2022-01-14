package com.rio.entity;

import java.util.List;

public record User(
        String id,
        String email,
        String password,
        List<String> organizationIds) {
}