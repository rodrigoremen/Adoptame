package com.example.adoptame;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableJpaAuditing
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class WebSecurityConfig {

    @Autowired
    private DataSource dataSource;

    public void globalConfigure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication(dataSource);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests((requests) -> {
            requests.requestMatchers("/", "/index").permitAll();
            requests.anyRequest().authenticated();
        });

        http.formLogin((login) -> {
            login.loginPage("/login").permitAll().defaultSuccessUrl("/index", true);

        });
        http.logout((logout) -> {
            logout.permitAll();
        });
        return http.build();
    }
}
