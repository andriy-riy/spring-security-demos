package com.rio.security;

public record JwtTokenResponse(String token, Long expiration) {
}
