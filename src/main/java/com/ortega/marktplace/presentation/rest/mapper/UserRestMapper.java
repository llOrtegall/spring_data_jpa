package com.ortega.marktplace.presentation.rest.mapper;

import com.ortega.marktplace.application.dto.request.CreateUserRequest;
import com.ortega.marktplace.application.dto.request.UpdateUserRequest;
import com.ortega.marktplace.presentation.rest.request.CreateUserRestRequest;
import com.ortega.marktplace.presentation.rest.request.UpdateUserRestRequest;
import org.springframework.stereotype.Component;

@Component
public class UserRestMapper {
    
    public CreateUserRequest toCreateRequest(CreateUserRestRequest restRequest) {
        return new CreateUserRequest(
            restRequest.firstName(),
            restRequest.lastName(),
            restRequest.email()
        );
    }
    
    public UpdateUserRequest toUpdateRequest(UpdateUserRestRequest restRequest) {
        return new UpdateUserRequest(
            restRequest.firstName(),
            restRequest.lastName()
        );
    }
}
