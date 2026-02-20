package com.ortega.marktplace.application.usecase.command;

import com.ortega.marktplace.application.port.input.DeleteUserUseCase;
import com.ortega.marktplace.domain.exception.UserNotFoundException;
import com.ortega.marktplace.domain.model.valueobjects.UserId;
import com.ortega.marktplace.domain.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DeleteUserUseCaseImpl implements DeleteUserUseCase {
    
    private final UserRepository userRepository;
    
    public DeleteUserUseCaseImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @Override
    public void execute(String id) {
        UserId userId = new UserId(id);
        
        if (!userRepository.findById(userId).isPresent()) {
            throw new UserNotFoundException(userId);
        }
        
        userRepository.deleteById(userId);
    }
}
