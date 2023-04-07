package com.example.adoptame;

import com.example.adoptame.application.entities.auditLog.services.LoginLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.sql.DataSource;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class WebSecurityConfig {

    @Autowired
    private DataSource dataSource;

    @Autowired
    LoginLog loginLog;

    @Autowired
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery("SELECT username, password, is_active FROM users WHERE username = ?").authoritiesByUsernameQuery("SELECT u.username, a.authority FROM roles a INNER JOIN permmissions p ON a.id_role = p.role_id INNER JOIN users u ON p.user_id = u.id_user WHERE u.username = ?").passwordEncoder(passwordEncoder());
    }

    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler() {
        return ((request, response, authentication) -> {
            String username = authentication.getName();
            loginLog.saveLogin(username);
        });
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests((requests) -> {
            requests.requestMatchers("/", "/index").permitAll();
            requests.anyRequest().authenticated();
        });

        http.formLogin((login) -> {
            login.loginPage("/login").permitAll().successHandler(authenticationSuccessHandler()).defaultSuccessUrl("/index").failureUrl("/login?error");

        });
        http.logout((logout) -> {
            logout.logoutSuccessUrl("/login?logout").permitAll();
        });
        return http.build();
    }
}
