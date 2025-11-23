package com.mcapijava.medcheck.dto;

import java.util.List;
import java.util.UUID;

public record DoctorResponse(
        UUID id,
        String name,
        String crm,
        String city,
        String specialty,
        String phone,
        String email
) {}
