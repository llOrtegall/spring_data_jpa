package com.ortega.marktplace.application.usecase.command;

import com.ortega.marktplace.application.dto.request.CreateUserRequest;
import com.ortega.marktplace.application.dto.response.UserResponse;
import com.ortega.marktplace.application.mapper.UserMapper;
import com.ortega.marktplace.application.port.input.CreateUserUseCase;
import com.ortega.marktplace.domain.exception.DuplicateEmailException;
import com.ortega.marktplace.domain.model.entities.User;
import com.ortega.marktplace.domain.model.valueobjects.Email;
import com.ortega.marktplace.domain.model.valueobjects.UserId;
import com.ortega.marktplace.domain.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CreateUserUseCaseImpl implements CreateUserUseCase {
    
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    
    public CreateUserUseCaseImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }
    
    @Override
    public UserResponse execute(CreateUserRequest request) {
        Email email = new Email(request.email());
        
        if (userRepository.existsByEmail(email)) {
            throw new DuplicateEmailException(request.email());
        }
        
        User user = new User(
            UserId.generate(),
            request.firstName(),
            request.lastName(),
            email
        );
        
        User savedUser = userRepository.save(user);
        return userMapper.toResponse(savedUser);
    }
}
