package com.mcapijava.medcheck.repository;

import com.mcapijava.medcheck.models.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface DoctorRepository extends JpaRepository<Doctor, UUID> {
}
