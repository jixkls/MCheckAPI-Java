package com.mcapijava.medcheck.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CityRequest(
   @NotBlank
   @Size(max = 80)
   String name,

   @NotBlank
   @Size(min = 2, max = 2)
   String state
) {}
