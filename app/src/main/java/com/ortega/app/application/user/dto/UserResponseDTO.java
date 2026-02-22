package com.ortega.app.application.user.dto;

import java.time.LocalDateTime;

public record UserResponseDTO(
    String id,
    String names,
    String email,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {
}
