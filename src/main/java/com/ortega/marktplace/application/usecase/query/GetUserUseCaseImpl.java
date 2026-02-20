package com.ortega.marktplace.application.usecase.query;

import com.ortega.marktplace.application.dto.response.UserResponse;
import com.ortega.marktplace.application.mapper.UserMapper;
import com.ortega.marktplace.application.port.input.GetUserUseCase;
import com.ortega.marktplace.domain.exception.UserNotFoundException;
import com.ortega.marktplace.domain.model.entities.User;
import com.ortega.marktplace.domain.model.valueobjects.Email;
import com.ortega.marktplace.domain.model.valueobjects.UserId;
import com.ortega.marktplace.domain.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class GetUserUseCaseImpl implements GetUserUseCase {
    
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    
    public GetUserUseCaseImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }
    
    @Override
    public UserResponse getById(String id) {
        UserId userId = new UserId(id);
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new UserNotFoundException(userId));
        return userMapper.toResponse(user);
    }
    
    @Override
    public UserResponse getByEmail(String email) {
        Email emailVO = new Email(email);
        User user = userRepository.findByEmail(emailVO)
            .orElseThrow(() -> new UserNotFoundException(email));
        return userMapper.toResponse(user);
    }
    
    @Override
    public List<UserResponse> getAll() {
        return userRepository.findAll().stream()
            .map(userMapper::toResponse)
            .collect(Collectors.toList());
    }
    
    @Override
    public List<UserResponse> getAllActive() {
        return userRepository.findAllActive().stream()
            .map(userMapper::toResponse)
            .collect(Collectors.toList());
    }
}
