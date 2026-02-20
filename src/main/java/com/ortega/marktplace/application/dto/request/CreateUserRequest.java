package com.ortega.marktplace.application.dto.request;

public record CreateUserRequest(
    String firstName,
    String lastName,
    String email
) {}
