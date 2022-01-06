package com.rio.security;

import lombok.Data;

@Data
public class JwtTokenResponse {

    private final String token;
    private final Long expiration;
}
