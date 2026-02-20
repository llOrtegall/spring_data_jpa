package com.ortega.marktplace.application.dto.response;

import java.time.LocalDateTime;

public record UserResponse(
    String id,
    String firstName,
    String lastName,
    String email,
    String fullName,
    boolean active,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {}
