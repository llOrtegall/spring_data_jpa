package com.ortega.marktplace.infrastructure.persistence.mapper;

import com.ortega.marktplace.domain.model.entities.User;
import com.ortega.marktplace.domain.model.valueobjects.Email;
import com.ortega.marktplace.domain.model.valueobjects.UserId;
import com.ortega.marktplace.infrastructure.persistence.entity.UserJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class UserPersistenceMapper {
    
    public UserJpaEntity toJpaEntity(User user) {
        return new UserJpaEntity(
            user.getId().getValue(),
            user.getFirstName(),
            user.getLastName(),
            user.getEmail().getValue(),
            user.isActive(),
            user.getCreatedAt(),
            user.getUpdatedAt()
        );
    }
    
    public User toDomain(UserJpaEntity entity) {
        return User.reconstitute(
            new UserId(entity.getId()),
            entity.getFirstName(),
            entity.getLastName(),
            new Email(entity.getEmail()),
            entity.isActive(),
            entity.getCreatedAt(),
            entity.getUpdatedAt()
        );
    }
}
