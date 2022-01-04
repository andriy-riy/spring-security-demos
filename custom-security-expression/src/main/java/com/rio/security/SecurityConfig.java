package com.rio.security;

import com.rio.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.AuthenticationEntryPointFailureHandler;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final UserService userService;
  private final String secret;

  public SecurityConfig(UserService userService, @Value("${security.authentication.jwt.secret}") String secret) {
    this.userService = userService;
    this.secret = secret;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .httpBasic().disable()
        .csrf().disable()
        .sessionManagement()
              .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .exceptionHandling()
                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
        .and()
        .authorizeRequests()
              .expressionHandler(new CustomDefaultWebSecurityExpressionHandler())
              .antMatchers("/api/v1/organizations/{id}/**").access("isMember(#id)");

    http.addFilterAt(jwtRequestFilter(), UsernamePasswordAuthenticationFilter.class);
    http.addFilterAfter(jwtTokenValidationFilter(), JwtAuthenticationFilter.class);
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) {
    auth.authenticationProvider(authenticationProvider());
  }

  private JwtAuthenticationFilter jwtRequestFilter() throws Exception {
    JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(
        new RegexRequestMatcher("/login", "POST"),
        new JwtAuthenticationSuccessHandler(),
        new AuthenticationEntryPointFailureHandler(new HttpStatusEntryPoint(HttpStatus.BAD_REQUEST))
    );

    jwtAuthenticationFilter.setAuthenticationManager(this.authenticationManager());

    return jwtAuthenticationFilter;
  }

  private JwtAuthenticationProvider authenticationProvider() {
    return new JwtAuthenticationProvider(userService, secret);
  }

  private JwtTokenValidationFilter jwtTokenValidationFilter() {
    return new JwtTokenValidationFilter(secret);
  }
}