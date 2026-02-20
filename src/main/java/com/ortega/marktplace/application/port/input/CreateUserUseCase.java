package com.ortega.marktplace.application.port.input;

import com.ortega.marktplace.application.dto.request.CreateUserRequest;
import com.ortega.marktplace.application.dto.response.UserResponse;

public interface CreateUserUseCase {
    UserResponse execute(CreateUserRequest request);
}
