package com.mcapijava.medcheck.controllers;

import com.mcapijava.medcheck.dto.CreateUserRequest;
import com.mcapijava.medcheck.models.Role;
import com.mcapijava.medcheck.models.UserAccount;
import com.mcapijava.medcheck.repository.UserAccountRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserAccountRepository userAccountRepository;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserAccountRepository userAccountRepository,
                          PasswordEncoder passwordEncoder) {
        this.userAccountRepository = userAccountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody CreateUserRequest request) {

        Role role = request.role() != null ? request.role() : Role.USER;

        UserAccount user = new UserAccount();
        user.setName(request.name());
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setRole(role);

        userAccountRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
