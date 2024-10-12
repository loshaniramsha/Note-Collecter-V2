package com.example.Note_Collecter_V2.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Value("${secure.username}")
    private String username;
    @Value("${secure.password}")
    private String password;
    @Value("${secure.role}")
    private String role;

    @Bean
    SecurityFilterChain securityFilterChain (HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests()
                .anyRequest().authenticated()
                .and()
                .httpBasic();
        return httpSecurity.build();
    }
    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(){
        UserDetails userDetails= User.withDefaultPasswordEncoder()
                .username(username)
                .password(password)
                .roles(role).build();
        return new InMemoryUserDetailsManager(userDetails);
    }
}
