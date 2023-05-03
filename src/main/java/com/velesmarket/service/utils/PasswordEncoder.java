package com.velesmarket.service.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoder {

    private final BCryptPasswordEncoder passwordEncoder;

    public PasswordEncoder() {
        passwordEncoder = new BCryptPasswordEncoder();
    }

    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }
}
