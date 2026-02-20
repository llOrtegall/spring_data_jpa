package com.ortega.marktplace.application.port.input;

import com.ortega.marktplace.application.dto.response.UserResponse;

import java.util.List;

public interface GetUserUseCase {
    UserResponse getById(String id);
    UserResponse getByEmail(String email);
    List<UserResponse> getAll();
    List<UserResponse> getAllActive();
}
