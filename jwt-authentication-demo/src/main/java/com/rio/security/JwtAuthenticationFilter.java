package com.rio.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.RequestMatcher;

public class JwtAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    public JwtAuthenticationFilter(RequestMatcher authenticationRequestMatcher, AuthenticationSuccessHandler successHandler, AuthenticationFailureHandler failureHandler) {
        super(authenticationRequestMatcher);
        setAuthenticationSuccessHandler(successHandler);
        setAuthenticationFailureHandler(failureHandler);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String email = request.getParameter("username");
        String password = request.getParameter("password");

        var authentication = new UsernamePasswordAuthenticationToken(email, password);
        authentication.setDetails(authenticationDetailsSource.buildDetails(request));

        return getAuthenticationManager().authenticate(authentication);
    }
}