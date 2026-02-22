package com.ortega.app.application.user;

import com.ortega.app.application.user.dto.UserRequestDTO;
import com.ortega.app.application.user.dto.UserResponseDTO;
import com.ortega.app.application.user.services.PasswordEncryptor;
import com.ortega.app.domain.user.User;
import com.ortega.app.domain.user.UserRepository;
import com.ortega.app.infrastructure.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserCases {
    final private PasswordEncryptor encryptor;
    final private UserRepository userRepo;
    final private UserMapper userMapper;

    public UserCases(UserRepository userRepo, PasswordEncryptor encryptor, UserMapper userMapper) {
        this.encryptor = encryptor;
        this.userRepo = userRepo;
        this.userMapper = userMapper;
    }

    // Crear nuevo usuario
    public UserResponseDTO createUser(UserRequestDTO request) {
        String hashedPassword = this.encryptor.hashPassword(request.password());
        User user = new User(request.names(), request.email(), hashedPassword);
        
        User savedUser = this.userRepo.save(user);
        return this.userMapper.toResponseDTO(savedUser);
    }

    // Obtener usuario por ID
    public UserResponseDTO getUserById(String id) {
        User user = this.userRepo.findById(id);
        return this.userMapper.toResponseDTO(user);
    }

    // Actualizar usuario
    public UserResponseDTO updateUser(String id, String names, String email) {
        User user = this.userRepo.findById(id);
        user.updateUser(names, email);
        
        User updatedUser = this.userRepo.save(user);
        return this.userMapper.toResponseDTO(updatedUser);
    }

    // Eliminar usuario
    public void deleteUser(String id) {
        this.userRepo.delete(id);
    }
}
