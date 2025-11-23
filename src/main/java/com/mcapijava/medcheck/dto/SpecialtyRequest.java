package com.mcapijava.medcheck.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SpecialtyRequest(
        @NotBlank
        @Size(max = 80)
        String name
) {}
