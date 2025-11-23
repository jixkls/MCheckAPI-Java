package com.mcapijava.medcheck.dto;

public record DoctorRequest(
   String name,
   String crm,
   String city,
   String specialty,
   String phone,
   String email
) {}
