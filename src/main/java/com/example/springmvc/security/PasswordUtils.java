package com.example.springmvc.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordUtils {

    private static final PasswordEncoder encoder = new BCryptPasswordEncoder();

    // Mã hóa mật khẩu
    public static String encodePassword(String rawPassword) {
        return encoder.encode(rawPassword);
    }

    // So sánh mật khẩu
    public static boolean checkPassword(String rawPassword, String encodedPassword) {
        return encoder.matches(rawPassword, encodedPassword);
    }
}

