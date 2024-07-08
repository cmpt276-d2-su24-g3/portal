package com.bhavjit.cmpt276.portal.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import com.bhavjit.cmpt276.portal.services.AuthUserService;

@Configuration
public class SecurityConfiguration {

private static final Logger logger = LoggerFactory.getLogger(SecurityConfiguration.class);

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, AuthUserService authUserService) throws Exception {
        logger.info("FILTER");
        http.csrf(Customizer.withDefaults())
            .authorizeHttpRequests(authz -> authz
                .requestMatchers("/").permitAll()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated())
            .oauth2Login(oauth2 -> oauth2
                .userInfoEndpoint(userInfo -> userInfo
                    .oidcUserService(authUserService)
            ))
            .logout(httpSecurityLogoutConfigurer -> httpSecurityLogoutConfigurer.logoutSuccessUrl("/"));
        return http.build();
    }
}
