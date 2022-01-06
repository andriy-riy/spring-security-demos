package com.rio.security;

import com.rio.entity.User;
import lombok.Data;

@Data
public class UserPrinciple {

    private final String email;
    private final User.Role role;
}
