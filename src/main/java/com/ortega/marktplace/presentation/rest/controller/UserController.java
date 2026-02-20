package com.ortega.marktplace.presentation.rest.controller;

import com.ortega.marktplace.application.dto.response.UserResponse;
import com.ortega.marktplace.application.port.input.CreateUserUseCase;
import com.ortega.marktplace.application.port.input.DeleteUserUseCase;
import com.ortega.marktplace.application.port.input.GetUserUseCase;
import com.ortega.marktplace.application.port.input.UpdateUserUseCase;
import com.ortega.marktplace.presentation.rest.mapper.UserRestMapper;
import com.ortega.marktplace.presentation.rest.request.CreateUserRestRequest;
import com.ortega.marktplace.presentation.rest.request.UpdateUserRestRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    
    private final CreateUserUseCase createUserUseCase;
    private final GetUserUseCase getUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;
    private final UserRestMapper restMapper;
    
    public UserController(CreateUserUseCase createUserUseCase,
                         GetUserUseCase getUserUseCase,
                         UpdateUserUseCase updateUserUseCase,
                         DeleteUserUseCase deleteUserUseCase,
                         UserRestMapper restMapper) {
        this.createUserUseCase = createUserUseCase;
        this.getUserUseCase = getUserUseCase;
        this.updateUserUseCase = updateUserUseCase;
        this.deleteUserUseCase = deleteUserUseCase;
        this.restMapper = restMapper;
    }
    
    @PostMapping
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody CreateUserRestRequest request) {
        UserResponse response = createUserUseCase.execute(restMapper.toCreateRequest(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable String id) {
        UserResponse response = getUserUseCase.getById(id);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/email/{email}")
    public ResponseEntity<UserResponse> getUserByEmail(@PathVariable String email) {
        UserResponse response = getUserUseCase.getByEmail(email);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<UserResponse> response = getUserUseCase.getAll();
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/active")
    public ResponseEntity<List<UserResponse>> getAllActiveUsers() {
        List<UserResponse> response = getUserUseCase.getAllActive();
        return ResponseEntity.ok(response);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(
            @PathVariable String id,
            @Valid @RequestBody UpdateUserRestRequest request) {
        UserResponse response = updateUserUseCase.execute(id, restMapper.toUpdateRequest(request));
        return ResponseEntity.ok(response);
    }
    
    @PatchMapping("/{id}/activate")
    public ResponseEntity<UserResponse> activateUser(@PathVariable String id) {
        UserResponse response = updateUserUseCase.activateUser(id);
        return ResponseEntity.ok(response);
    }
    
    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<UserResponse> deactivateUser(@PathVariable String id) {
        UserResponse response = updateUserUseCase.deactivateUser(id);
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        deleteUserUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}
