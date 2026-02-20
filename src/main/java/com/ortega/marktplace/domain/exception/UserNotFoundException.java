package com.ortega.marktplace.domain.exception;

import com.ortega.marktplace.domain.model.valueobjects.UserId;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(UserId userId) {
        super("User not found with ID: " + userId);
    }
    
    public UserNotFoundException(String email) {
        super("User not found with email: " + email);
    }
}
