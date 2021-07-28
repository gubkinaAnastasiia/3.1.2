package com.gubkina.springbootsite.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService service;
    private final UserSuccessHandler handler;

    @Autowired
    public SecurityConfig(UserDetailsService service, UserSuccessHandler handler) {
        this.service = service;
        this.handler = handler;
    }

    public void configureGlobalSecurity(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(service).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.formLogin().successHandler(handler).loginProcessingUrl("/").usernameParameter("username").passwordParameter("password").permitAll();
        http.logout().permitAll().logoutRequestMatcher(new AntPathRequestMatcher("/login?logout"));
        http.authorizeRequests().antMatchers("/").anonymous().
                antMatchers("/admin").access("hasAnyRole('ADMIN')").
                antMatchers("/admin/**").access("hasAnyRole('ADMIN')").
                antMatchers("/user").access("hasAnyRole('USER', 'ADMIN')").
                antMatchers("/user/**").access("hasAnyRole('USER', 'ADMIN')");
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

}
