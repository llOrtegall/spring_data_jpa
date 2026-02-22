package com.ortega.app.infrastructure.mapper;

import com.ortega.app.application.user.dto.UserResponseDTO;
import com.ortega.app.domain.user.User;
import com.ortega.app.infrastructure.user.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class UserMapper {
    // Domain to Infrastructure
    public abstract UserEntity toEntity(User user);

    // Infrastructure to Domain
    public User toDomain(UserEntity entity) {
        if (entity == null) {
            return null;
        }
        return new User(entity.getId(), entity.getNames(), entity.getEmail(), 
                        entity.getPassword(), entity.getCreatedAt(), entity.getUpdatedAt());
    }

    // Domain to Response DTO
    public abstract UserResponseDTO toResponseDTO(User user);
}
