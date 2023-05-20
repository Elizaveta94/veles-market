package com.velesmarket.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                // Spring Security should completely ignore URLs starting with /resources/ , /webjars/
                .antMatchers("/resources/**", "/webjars/**");
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .authorizeRequests(authorize -> authorize
                        .antMatchers("/login", "/registration", "/", "/home", "/feature/**").permitAll()
                        .anyRequest().authenticated())
                .formLogin(formLogin -> formLogin
                        .usernameParameter("Login")
                        .passwordParameter("password")
                        .loginPage("/login")
                        .failureHandler(new DebugErrorHandler())
                        .permitAll())
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .deleteCookies("JSESSIONID")
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true)
                        .permitAll())
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    static class DebugErrorHandler implements AuthenticationFailureHandler {

        @Override
        public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
            System.out.println("error");
        }
    }

}
