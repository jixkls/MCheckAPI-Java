package com.mcapijava.medcheck.controllers;

import com.mcapijava.medcheck.dto.DoctorRequest;
import com.mcapijava.medcheck.dto.DoctorResponse;
import com.mcapijava.medcheck.models.Doctor;
import com.mcapijava.medcheck.repository.DoctorRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/doctors")
public class DoctorController {

    private final DoctorRepository doctorRepo;

    public DoctorController(DoctorRepository doctorRepo) {
        this.doctorRepo = doctorRepo;
    }

    @GetMapping
    public Page<DoctorResponse> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        if (size > 50) size = 50;

        var pageable = PageRequest.of(page, size, Sort.by("name").ascending());
        return doctorRepo.findAll(pageable).map(this::toResponse);
    }

    @GetMapping("/{id}")
    public DoctorResponse get(@PathVariable UUID id) {
        var doctor = doctorRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Doutor não encontrado"));

        return toResponse(doctor);
    }

    @PostMapping
    public DoctorResponse create(@Valid @RequestBody DoctorRequest request) {
        var doctor = new Doctor();

        doctor.setName(request.name());
        doctor.setCrm(request.crm());
        doctor.setCity(request.city());
        doctor.setSpecialty(request.specialty());
        doctor.setPhone(request.phone());
        doctor.setEmail(request.email());

        var saved = doctorRepo.save(doctor);
        return toResponse(saved);
    }

    @PutMapping("/{id}")
    public DoctorResponse update(@PathVariable UUID id, @Valid @RequestBody DoctorRequest request) {

        var doctor = doctorRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Doutor não encontrado"));

        doctor.setName(request.name());
        doctor.setCrm(request.crm());
        doctor.setCity(request.city());
        doctor.setSpecialty(request.specialty());
        doctor.setPhone(request.phone());
        doctor.setEmail(request.email());

        var updated = doctorRepo.save(doctor);
        return toResponse(updated);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        if (!doctorRepo.existsById(id)) {
            throw new RuntimeException("Doutor não encontrado");
        }
        doctorRepo.deleteById(id);
    }


    // Helper: converter Doctor para um DoctorResponse

    private DoctorResponse toResponse(Doctor doctor) {
        return new DoctorResponse(
                doctor.getId(),
                doctor.getName(),
                doctor.getCrm(),
                doctor.getCity(),
                doctor.getSpecialty(),
                doctor.getPhone(),
                doctor.getEmail()
        );
    }
}