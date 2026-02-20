package com.ortega.marktplace.application.port.input;

import com.ortega.marktplace.application.dto.request.UpdateUserRequest;
import com.ortega.marktplace.application.dto.response.UserResponse;

public interface UpdateUserUseCase {
    UserResponse execute(String id, UpdateUserRequest request);
    UserResponse activateUser(String id);
    UserResponse deactivateUser(String id);
}
