package com.rio.security;

import lombok.Data;

import java.util.List;

@Data
public class UserPrinciple {

    private final String id;
    private final String email;
    private final List<String> organizationIds;
}
