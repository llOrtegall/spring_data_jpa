package com.ortega.marktplace.application.usecase.command;

import com.ortega.marktplace.application.dto.request.UpdateUserRequest;
import com.ortega.marktplace.application.dto.response.UserResponse;
import com.ortega.marktplace.application.mapper.UserMapper;
import com.ortega.marktplace.application.port.input.UpdateUserUseCase;
import com.ortega.marktplace.domain.exception.UserNotFoundException;
import com.ortega.marktplace.domain.model.entities.User;
import com.ortega.marktplace.domain.model.valueobjects.UserId;
import com.ortega.marktplace.domain.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UpdateUserUseCaseImpl implements UpdateUserUseCase {
    
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    
    public UpdateUserUseCaseImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }
    
    @Override
    public UserResponse execute(String id, UpdateUserRequest request) {
        UserId userId = new UserId(id);
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new UserNotFoundException(userId));
        
        user.updateProfile(request.firstName(), request.lastName());
        
        User updatedUser = userRepository.save(user);
        return userMapper.toResponse(updatedUser);
    }
    
    @Override
    public UserResponse activateUser(String id) {
        UserId userId = new UserId(id);
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new UserNotFoundException(userId));
        
        user.activate();
        
        User updatedUser = userRepository.save(user);
        return userMapper.toResponse(updatedUser);
    }
    
    @Override
    public UserResponse deactivateUser(String id) {
        UserId userId = new UserId(id);
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new UserNotFoundException(userId));
        
        user.deactivate();
        
        User updatedUser = userRepository.save(user);
        return userMapper.toResponse(updatedUser);
    }
}
