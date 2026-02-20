package com.ortega.marktplace.domain.model.entities;

import com.ortega.marktplace.domain.model.valueobjects.Email;
import com.ortega.marktplace.domain.model.valueobjects.UserId;

import java.time.LocalDateTime;
import java.util.Objects;

public class User {
    private final UserId id;
    private String firstName;
    private String lastName;
    private Email email;
    private boolean active;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    public User(UserId id, String firstName, String lastName, Email email) {
        this.id = Objects.requireNonNull(id, "User ID cannot be null");
        this.firstName = validateName(firstName, "First name");
        this.lastName = validateName(lastName, "Last name");
        this.email = Objects.requireNonNull(email, "Email cannot be null");
        this.active = true;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
    
    private User(UserId id, String firstName, String lastName, Email email, 
                 boolean active, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.active = active;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    
    public static User reconstitute(UserId id, String firstName, String lastName, 
                                   Email email, boolean active, 
                                   LocalDateTime createdAt, LocalDateTime updatedAt) {
        return new User(id, firstName, lastName, email, active, createdAt, updatedAt);
    }
    
    public void updateProfile(String firstName, String lastName) {
        this.firstName = validateName(firstName, "First name");
        this.lastName = validateName(lastName, "Last name");
        this.updatedAt = LocalDateTime.now();
    }
    
    public void updateEmail(Email email) {
        this.email = Objects.requireNonNull(email, "Email cannot be null");
        this.updatedAt = LocalDateTime.now();
    }
    
    public void activate() {
        this.active = true;
        this.updatedAt = LocalDateTime.now();
    }
    
    public void deactivate() {
        this.active = false;
        this.updatedAt = LocalDateTime.now();
    }
    
    public String getFullName() {
        return firstName + " " + lastName;
    }
    
    private String validateName(String name, String fieldName) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(fieldName + " cannot be null or empty");
        }
        if (name.length() < 2) {
            throw new IllegalArgumentException(fieldName + " must be at least 2 characters");
        }
        return name.trim();
    }
    
    public UserId getId() {
        return id;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public Email getEmail() {
        return email;
    }
    
    public boolean isActive() {
        return active;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
