package com.mcapijava.medcheck.repository;

import com.mcapijava.medcheck.models.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecialtyRepository extends JpaRepository<Specialty, Long> {}
