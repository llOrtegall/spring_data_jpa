package com.ortega.app.presentation.user;

import com.ortega.app.application.user.UserCases;
import com.ortega.app.application.user.dto.UserRequestDTO;
import com.ortega.app.application.user.dto.UserResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserCases userCases;

    public UserController(UserCases userCases) {
        this.userCases = userCases;
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO request) {
        UserResponseDTO response = this.userCases.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable String id) {
        UserResponseDTO response = this.userCases.getUserById(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(
            @PathVariable String id,
            @RequestBody UserRequestDTO request) {
        UserResponseDTO response = this.userCases.updateUser(id, request.names(), request.email());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        this.userCases.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
