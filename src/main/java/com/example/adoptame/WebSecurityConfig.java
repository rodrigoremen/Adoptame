package com.example.adoptame;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableJpaAuditing
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class WebSecurityConfig {

    @Autowired
    private DataSource dataSource;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public void globalConfigure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery("SELECT username, password, enabled FROM users WHERE username = ?").passwordEncoder(passwordEncoder()).authoritiesByUsernameQuery("SELECT u.username, r.authority FROM authorities a INNER JOIN users u ON u.id_user = a.user_id INNER JOIN roles r ON r.id_rol = a.rol_id WHERE u.username = ?");
        ;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests((requests) -> {
            requests.requestMatchers("/", "/index").permitAll();
            requests.anyRequest().authenticated();
        });

        http.formLogin((login) -> {
            login.loginPage("/login").permitAll().defaultSuccessUrl("/index").failureUrl("/login-error");

        });
        http.logout((logout) -> {
            logout.logoutSuccessUrl("/login?logout").permitAll();
        });
        return http.build();
    }
}
