package com.ortega.app.application.user.dto;

public record UserRequestDTO(
    String names,
    String email,
    String password
) {
}
