package com.ortega.app.infrastructure.mapper;

import com.ortega.app.application.user.dto.UserResponseDTO;
import com.ortega.app.domain.user.User;
import com.ortega.app.infrastructure.user.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    // Domain to Infrastructure
    UserEntity toEntity(User user);

    // Infrastructure to Domain
    User toDomain(UserEntity entity);

    // Domain to Response DTO
    UserResponseDTO toResponseDTO(User user);
}
