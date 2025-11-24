package com.mcapijava.medcheck.dto;

import com.mcapijava.medcheck.models.Role;

public record CreateUserRequest(
        String name,
        String email,
        String password,
        Role role
) {}