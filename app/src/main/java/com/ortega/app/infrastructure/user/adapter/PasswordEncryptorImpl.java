package com.ortega.app.infrastructure.user.adapter;

import com.ortega.app.application.user.services.PasswordEncryptor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncryptorImpl implements PasswordEncryptor {
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public String hashPassword(String password) {
        return encoder.encode(password);
    }

    @Override
    public boolean compare(String password, String hashPass) {
        return encoder.matches(password, hashPass);
    }
}
