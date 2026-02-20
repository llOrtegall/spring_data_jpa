package com.ortega.marktplace.application.mapper;

import com.ortega.marktplace.application.dto.response.UserResponse;
import com.ortega.marktplace.domain.model.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    
    public UserResponse toResponse(User user) {
        return new UserResponse(
            user.getId().toString(),
            user.getFirstName(),
            user.getLastName(),
            user.getEmail().getValue(),
            user.getFullName(),
            user.isActive(),
            user.getCreatedAt(),
            user.getUpdatedAt()
        );
    }
}
