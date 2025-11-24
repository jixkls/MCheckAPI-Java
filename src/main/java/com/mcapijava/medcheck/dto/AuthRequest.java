package com.mcapijava.medcheck.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AuthRequest(
        String email,
        String password
) {}