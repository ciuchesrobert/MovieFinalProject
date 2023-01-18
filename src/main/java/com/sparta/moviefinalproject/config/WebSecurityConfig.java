package com.sparta.moviefinalproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

public class WebSecurityConfig {
    @Configuration
    @EnableWebSecurity
    public class SpringWebSecurityConfig {
        @Bean
        //Creates user registry
        public InMemoryUserDetailsManager configureUsers() {
            UserDetails admin = User.withDefaultPasswordEncoder()
                    .username("admin")
                    .password("admin")
                    .roles("ADMIN")
                    .build();
            UserDetails basic = User.withDefaultPasswordEncoder()
                    .username("basic")
                    .password("basic")
                    .roles("BASIC")
                    .build();
            return new InMemoryUserDetailsManager(admin, basic);

        }

        // Sets up the security policy - authorisation
        @Bean
        public SecurityFilterChain configurePolicy(HttpSecurity http) throws Exception {
            return http.authorizeRequests(auth -> {
                        auth.requestMatchers("/comments/basic/**",
                                        "/movies/basic/**",
                                        "/theaters/basic/**",
                                        "/users/basic/**")
                                .hasRole("ADMIN");

                        auth.requestMatchers("/comments/admin/**",
                                        "/movies/admin/**",
                                        "/theaters/admin/**",
                                        "/users/admin/**")
                                .hasAnyRole("ADMIN", "BASIC");
                    })
                    .formLogin().loginPage("/login")
                    .successForwardUrl("/home") // better to send to where user was going
                    .permitAll()
                    .and()
                    .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login")
                    .deleteCookies()
                    .and()
                    .rememberMe()
                    .and()
                    .build();
        }
    }
}
