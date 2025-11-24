package com.mcapijava.medcheck.dto;

public record DoctorUpdateRequest(
        String name,
        String crm,
        String city,
        String specialty,
        String phone,
        String email
) {}