package com.ortega.app.application.user.services;

public interface PasswordEncryptor {
    String hashPassword(String password);
    boolean compare(String password, String hashPass);
}
