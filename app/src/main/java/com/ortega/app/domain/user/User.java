package com.ortega.app.domain.user;

import java.time.LocalDateTime;
import java.util.UUID;

public class User {
    final private String id;
    private String names;
    private String email;
    private String password;
    final private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Constructor para crear nuevo usuario (domain logic)
    public User(String names, String email, String password) {
        this.id = UUID.randomUUID().toString();
        this.names = names;
        this.email = email;
        this.password = password;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    // Constructor para reconstruir usuario desde persistencia
    public User(String id, String names, String email, String password, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.names = names;
        this.email = email;
        this.password = password;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Métodos de negocio (no setters)
    public void updateUser(String names, String email) {
        this.names = names;
        this.email = email;
        this.updatedAt = LocalDateTime.now();
    }

    // Cambiar password (requiere validación de contraseña actual en app layer)
    public void changePassword(String hashedPassword) {
        this.password = hashedPassword;
        this.updatedAt = LocalDateTime.now();
    }

    // Getters (solo lectura)
    public String getId() {
        return id;
    }

    public String getNames() {
        return names;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
