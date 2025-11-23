package com.mcapijava.medcheck.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DoctorRequest(
   @NotBlank
   @Size(max = 120)
   String name,

   @NotBlank
   @Size(max = 20)
   String crm,

   @NotBlank
   @Size(max = 80)
   String city,

   @NotBlank
   @Size(max = 80)
   String specialty,

   @Size(max = 30)
   String phone,

   @Email
   @Size(max = 120)
   String email
) {}
