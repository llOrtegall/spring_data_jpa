package com.ortega.marktplace.infrastructure.persistence.repository;

import com.ortega.marktplace.domain.model.entities.User;
import com.ortega.marktplace.domain.model.valueobjects.Email;
import com.ortega.marktplace.domain.model.valueobjects.UserId;
import com.ortega.marktplace.domain.repository.UserRepository;
import com.ortega.marktplace.infrastructure.persistence.entity.UserJpaEntity;
import com.ortega.marktplace.infrastructure.persistence.mapper.UserPersistenceMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class UserRepositoryImpl implements UserRepository {
    
    private final UserJpaRepository jpaRepository;
    private final UserPersistenceMapper mapper;
    
    public UserRepositoryImpl(UserJpaRepository jpaRepository, UserPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }
    
    @Override
    public User save(User user) {
        UserJpaEntity entity = mapper.toJpaEntity(user);
        UserJpaEntity savedEntity = jpaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }
    
    @Override
    public Optional<User> findById(UserId id) {
        return jpaRepository.findById(id.getValue())
            .map(mapper::toDomain);
    }
    
    @Override
    public Optional<User> findByEmail(Email email) {
        return jpaRepository.findByEmail(email.getValue())
            .map(mapper::toDomain);
    }
    
    @Override
    public List<User> findAll() {
        return jpaRepository.findAll().stream()
            .map(mapper::toDomain)
            .collect(Collectors.toList());
    }
    
    @Override
    public List<User> findAllActive() {
        return jpaRepository.findAllActive().stream()
            .map(mapper::toDomain)
            .collect(Collectors.toList());
    }
    
    @Override
    public void deleteById(UserId id) {
        jpaRepository.deleteById(id.getValue());
    }
    
    @Override
    public boolean existsByEmail(Email email) {
        return jpaRepository.existsByEmail(email.getValue());
    }
}
