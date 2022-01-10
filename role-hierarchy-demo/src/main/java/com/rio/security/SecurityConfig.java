package com.rio.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rio.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.authentication.AuthenticationEntryPointFailureHandler;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.time.Duration;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;
    private final ObjectMapper objectMapper;
    private final String secret;
    private final Duration tokenExpiration;

    public SecurityConfig(UserService userService,
                          ObjectMapper objectMapper,
                          @Value("${security.authentication.jwt.secret}") String secret,
                          @Value("${security.authentication.jwt.expiration}") Duration tokenExpiration) {

        this.userService = userService;
        this.objectMapper = objectMapper;
        this.secret = secret;
        this.tokenExpiration = tokenExpiration;
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
                    .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));
//                .and()
//                .authorizeRequests()
//                    .expressionHandler(webExpressionHandler())
//                    .antMatchers("/ping-admin").hasRole("ADMIN")
//                    .antMatchers("/ping-employee").hasAnyRole("ADMIN", "EMPLOYEE")
//                    .antMatchers("/ping-guest").hasAnyRole("ADMIN", "EMPLOYEE", "GUEST");

        http.addFilterAfter(jwtTokenValidationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(new MongoDBAuthenticationProvider(userService));
    }

    @Bean
    public UsernamePasswordAuthenticationFilter usernamePasswordAuthenticationFilter() throws Exception {
        var usernamePasswordAuthenticationFilter = new UsernamePasswordAuthenticationFilter();
        usernamePasswordAuthenticationFilter.setAuthenticationManager(this.authenticationManager());
        usernamePasswordAuthenticationFilter.setAuthenticationSuccessHandler(new JwtAuthenticationSuccessHandler(objectMapper, secret, tokenExpiration));
        usernamePasswordAuthenticationFilter.setAuthenticationFailureHandler(new AuthenticationEntryPointFailureHandler(new HttpStatusEntryPoint(HttpStatus.BAD_REQUEST)));

        return usernamePasswordAuthenticationFilter;
    }

    @Bean
    public JwtBearerTokenFilter jwtTokenValidationFilter() {
        return new JwtBearerTokenFilter(secret);
    }

    private SecurityExpressionHandler<FilterInvocation> webExpressionHandler() {
        DefaultWebSecurityExpressionHandler defaultWebSecurityExpressionHandler = new DefaultWebSecurityExpressionHandler();
        defaultWebSecurityExpressionHandler.setRoleHierarchy(roleHierarchy());

        return defaultWebSecurityExpressionHandler;
    }

    private RoleHierarchyImpl roleHierarchy() {
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        roleHierarchy.setHierarchy("ROLE_ADMIN > ROLE_EMPLOYEE > ROLE_GUEST");

        return roleHierarchy;
    }
}